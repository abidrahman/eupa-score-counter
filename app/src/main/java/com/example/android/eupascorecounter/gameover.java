package com.example.android.eupascorecounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Abid on 2015-07-11.
 */

public class gameover extends AppCompatActivity {

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.game_over);

    }

    /**
     * Goes back to the score screen when button is clicked.
     */

    public void backToScores(View v) {
        finish();
    }
}
