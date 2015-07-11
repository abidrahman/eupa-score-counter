package com.example.android.eupascorecounter;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;


/**
 * Created by Abid on 2015-07-10.
 */

public class timer extends AppCompatActivity {

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.timer);

    }

    /**
     * Goes to the timer screen when timeout is clicked.
     */

    public void backToScores(View v) {
        finish();
    }
}
