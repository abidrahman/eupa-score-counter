package com.example.android.eupascorecounter;


        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;

/**
 * This activity keeps track of the ultimate frisbee score for 2 teams.
 */
public class MainActivity extends AppCompatActivity {

    // Tracks the score for Team A
    int homeScore = 0;

    // Tracks the score for Team B
    int awayScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jamesbond = "hi";
        String jamesBond = "hello";
        String s = jamesBond + jamesbond;
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
     * Increase the score for the Home Team by 1 point.
     */
    public void addOneForHome(View v) {
        homeScore = homeScore + 1;
        displayForTeamA(homeScore);
    }


    /**
     * Increase the score for the Away Team by 1 point.
     */
    public void addOneForAway(View v) {
        awayScore = awayScore + 1;
        displayForTeamB(awayScore);
    }

    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {
        homeScore = 0;
        awayScore = 0;
        displayForTeamA(homeScore);
        displayForTeamB(awayScore);
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