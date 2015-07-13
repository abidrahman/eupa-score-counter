package com.example.android.eupascorecounter;


        import android.app.Fragment;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

/**
 * This activity keeps track of the ultimate frisbee score for 2 teams.
 */
public class MainActivity extends AppCompatActivity {


    // Tracks the score for home team.
    int homeScore = 0;

    // Tracks the score for away team.
    int awayScore = 0;

    // Tracks whether or not we have had halftime.
    boolean htime = false;
    public TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimpSlifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Goes to the timer screen when it is halftime (first to 8 points).
     */

    public void halftime(int awayScore, int homeScore, View v) {
        this.awayScore = awayScore;
        this.homeScore = homeScore;
        if (((homeScore == 8 && awayScore < 8) || (awayScore == 8 && homeScore < 8)) && !htime) {
            Intent intent = new Intent(this, timer.class);
            startActivityForResult(intent, 0);
            htime = true;

        }
    }

    /**
     * Goes to the GameOver screen once a team reaches 15 points, also resets the score.
     */



    public void gameOver(int awayScore, int homeScore, View v) {
        this.awayScore = awayScore;
        this.homeScore = homeScore;


        if (homeScore == 15 && awayScore < 14) {
            Intent intent = new Intent(v.getContext(), game_over.class);
            intent.putExtra("winner","Home Team Wins!");
            startActivityForResult(intent, 0);
            resetScore(v);


        } else if (awayScore == 15 && homeScore < 14) {
            Intent intent = new Intent(v.getContext(), game_over.class);
            intent.putExtra("winner", "Away Team Wins!");
            startActivityForResult(intent, 0);
            resetScore(v);


        }
    }


    /**
     * Goes to the timer screen.
     */

    public void timeout(View v) {
        Intent intent = new Intent(v.getContext(), timer.class);
        startActivityForResult(intent, 0);
    }

    /**
     * Increase the score for the Home Team by 1 point.
     */
    public void addOneForHome(View v) {
        homeScore = homeScore + 1;
        displayForTeamA(homeScore);
        halftime(awayScore, homeScore, v);
        gameOver(awayScore, homeScore, v);
    }


    /**
     * Increase the score for the Away Team by 1 point.
     */
    public void addOneForAway(View v) {
        awayScore = awayScore + 1;
        displayForTeamB(awayScore);
        halftime(awayScore, homeScore, v);
        gameOver(awayScore, homeScore, v);
    }

    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {
        homeScore = 0;
        awayScore = 0;
        displayForTeamA(homeScore);
        displayForTeamB(awayScore);
        htime = false;
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.away_score);
        scoreView.setText(String.valueOf(score));
    }


}