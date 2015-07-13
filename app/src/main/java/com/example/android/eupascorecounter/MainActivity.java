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

    // Tracks whether or not timeout has been taken per half for each team.
    boolean Atime1 = false;
    boolean Atime2 = false;
    boolean Htime1 = false;
    boolean Htime2 = false;


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
            Intent intent = new Intent(v.getContext(), timer.class);
            intent.putExtra("text", "Halftime!");
            intent.putExtra("time", 600000);
            startActivityForResult(intent, 0);
            htime = true;

        }
    }

    /**
     * Goes to the GameOver screen once a team reaches 15 points, also resets the score and sends the winner to the GameOver screen.
     */
    public void gameOver(int awayScore, int homeScore, View v) {
        this.awayScore = awayScore;
        this.homeScore = homeScore;


        if ((homeScore == 15 && awayScore < 14) || (awayScore > 13 && homeScore == awayScore + 2)) {
            Intent intent = new Intent(v.getContext(), game_over.class);
            intent.putExtra("winner","Home Team Wins!");
            intent.putExtra("wscore",homeScore);
            intent.putExtra("lscore",awayScore);
            startActivityForResult(intent, 0);
            resetScore(v);


        } else if ((awayScore == 15 && homeScore < 14) || (homeScore > 13 && awayScore == homeScore + 2)) {
            Intent intent = new Intent(v.getContext(), game_over.class);
            intent.putExtra("winner", "Away Team Wins!");
            intent.putExtra("lscore",homeScore);
            intent.putExtra("wscore",awayScore);
            startActivityForResult(intent, 0);
            resetScore(v);


        }
    }

    /**
     * Checks and executes timer screen depending on whether or not timeout has been taken. (Away Team)
     */
    public void timeoutA(View v) {
        if (!htime && !Atime1) {
            Intent intent = new Intent(v.getContext(), timer.class);
            intent.putExtra("text", "Away Team Timeout!");
            intent.putExtra("time", 70000);
            startActivityForResult(intent, 0);
            Atime1 = true;
        } else if (htime && !Atime2) {
            Intent intent = new Intent(v.getContext(), timer.class);
            intent.putExtra("text", "Away Team Timeout!");
            intent.putExtra("time", 70000);
            startActivityForResult(intent, 0);
            Atime2 = true;
        } else {
            TextView scoreView = (TextView) findViewById(R.id.liveText);
            scoreView.setText(String.valueOf("Away Timeout Already Taken!"));
        }

    }

    /**
     * Checks and executes timer screen depending on whether or not timeout has been taken. (Home Team)
     */
    public void timeoutH(View v) {
        if (!htime && !Htime1) {
            Intent intent = new Intent(v.getContext(), timer.class);
            intent.putExtra("text", "Home Team Timeout!");
            intent.putExtra("time", 70000);
            startActivityForResult(intent, 0);
            Htime1 = true;
        } else if (htime && !Htime2) {
            Intent intent = new Intent(v.getContext(), timer.class);
            intent.putExtra("text", "Home Team Timeout!");
            intent.putExtra("time", 70000);
            startActivityForResult(intent, 0);
            Htime2 = true;
        } else {
            TextView scoreView = (TextView) findViewById(R.id.liveText);
            scoreView.setText(String.valueOf("Home Timeout Already Taken!"));
        }

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
        TextView scoreView = (TextView) findViewById(R.id.liveText);
        scoreView.setText(String.valueOf("Welcome to the Game!"));
        htime = false;
        Atime1 = false;
        Atime2 = false;
        Htime1 = false;
        Htime2 = false;
    }

    /**
     * Displays the given score for Team A and updates LiveText.
     */
    public void displayForTeamA(int score) {
        TextView liveText = (TextView) findViewById(R.id.liveText);
        liveText.setText(String.valueOf("Home Team Scores!"));
        TextView scoreView = (TextView) findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B and updates LiveText.
     */
    public void displayForTeamB(int score) {
        TextView liveText = (TextView) findViewById(R.id.liveText);
        liveText.setText(String.valueOf("Away Team Scores!"));
        TextView scoreView = (TextView) findViewById(R.id.away_score);
        scoreView.setText(String.valueOf(score));
    }


}