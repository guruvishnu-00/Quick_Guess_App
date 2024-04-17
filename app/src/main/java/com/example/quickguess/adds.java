package com.example.quickguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class adds extends AppCompatActivity {

    TextView Score;
    TextView Life;
    TextView Time;

    TextView questions;
    EditText answer;

    Button okay;
    Button Next;

    Random random = new Random();
    int number1;
    int number2;
    int useranswer;
    int userscore = 0;
    int realanswer;
    int userlife = 3;

    CountDownTimer timer;
    private  static final long START_TIMER_IN_MILLIS = 45000;
    boolean timer_running;
    long time_left_in_millis = START_TIMER_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adds);

        Score = findViewById(R.id.textViewScore);
        Life = findViewById(R.id.textViewLife);
        Time = findViewById(R.id.textViewTime);

        questions = findViewById(R.id.textViewquestion);
        answer = findViewById(R.id.editTextTextPersonName);
        okay = findViewById(R.id.buttonOkay);
        Next = findViewById(R.id.buttonNext);

        gamecontinue();

        answer.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().trim().length()==0){
                    okay.setEnabled(false);
                } else {
                    okay.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    okay.setEnabled(false);
                } else {
                    okay.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                useranswer = Integer.valueOf(answer.getText().toString());
                answer.setText("");
                pausetimer();
                if(useranswer == realanswer)
                {
                    userscore = userscore + 10;
                    Score.setText(""+userscore);
                    questions.setText("Your answer is correct");
                    resettimer();
                    okay.setEnabled(false);
                    Next.setEnabled(true);
                }

                else
                {
                    userlife = userlife - 1;
                    Life.setText("" + userlife);
                    questions.setText("Wrong answer");
                    resettimer();
                    okay.setEnabled(false);
                    answer.setEnabled(false);
                    Next.setEnabled(true);

                }
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText("");

                resettimer();
                okay.setClickable(true);
                answer.setEnabled(true);
                Next.setEnabled(false);

                if (userlife <= 0)
                {
                    Toast.makeText(getApplicationContext(),"GAME OVER",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(adds.this,result.class);
                    intent.putExtra("Score",userscore);
                    startActivity(intent);
                    finish();
                }

                else
                {
                    gamecontinue();
                    okay.setEnabled(true);

                }

            }
        });

    }
    public void gamecontinue()
    {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        realanswer = number1 + number2;

        questions.setText(number1 + " + " + number2);
        starttimer();
        Next.setEnabled(false);

    }

    public  void starttimer()
    {
        timer = new CountDownTimer(time_left_in_millis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_millis = millisUntilFinished;
                updatetext();


            }

            @Override
            public void onFinish() {
                timer_running = false;
                pausetimer();
                resettimer();
                updatetext();
                userlife = userlife - 1;
                Life.setText(""+userlife);
                questions.setText("Time is up! ");
            }
        }.start();
        timer_running = true;
    }
    public  void updatetext()
    {
        int second = (int) (time_left_in_millis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        Time.setText(time_left);

    }
    public  void  pausetimer()
    {
        timer.cancel();
        timer_running = false;
    }
    public  void  resettimer()
    {
        time_left_in_millis = START_TIMER_IN_MILLIS;
        updatetext();
    }

}
