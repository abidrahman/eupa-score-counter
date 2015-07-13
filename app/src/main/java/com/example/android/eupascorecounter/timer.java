package com.example.android.eupascorecounter;


        import android.content.Intent;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.TextView;

        import java.util.concurrent.TimeUnit;


/**
 * Created by Abid on 2015-07-10.
 */




public class timer extends AppCompatActivity {

    TextView textViewTime;



    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.timer);
        String text1 = getIntent().getExtras().getString("text");
        showText(text1);
        textViewTime = (TextView) findViewById(R.id.timerValue);
        int time = getIntent().getExtras().getInt("time");
        final CounterClass timer = new CounterClass(time, 1000);
        timer.start();
    }

    public void backToScores(View v) {
        finish();
    }

    public void showText(String text2) {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(String.valueOf(text2));
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String msms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                    TimeUnit.MILLISECONDS.toMillis(millis) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)));
            System.out.println(msms);
            textViewTime.setText(msms);
        }

        public void onFinish() {
            textViewTime.setText("Done!");
        }

    }



}
