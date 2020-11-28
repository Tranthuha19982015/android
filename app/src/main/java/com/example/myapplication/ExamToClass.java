package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DataBaseManager;
import com.example.myapplication.db.Question;

import java.util.ArrayList;

public class ExamToClass extends AppCompatActivity implements View.OnClickListener {
    CountDownTimer countDownTimer;
    TextView txtscore, txtquestion, txttime;
    Button btncasea, btncaseb, btncasec;
    private DataBaseManager dataBaseManager;
    private int score;
    private Question question;
    private int trueCase;
    private int luachon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examtoclass);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        score = 0;
        txtscore = (TextView) findViewById(R.id.txtScore);
        txttime = (TextView) findViewById(R.id.txtTimedown);
        txtquestion = (TextView) findViewById(R.id.txtQuestion);
        btncasea = (Button) findViewById(R.id.btnCaseA);
        btncaseb = (Button) findViewById(R.id.btnCaseB);
        btncasec = (Button) findViewById(R.id.btnCaseC);

        btncasea.setOnClickListener(this);
        btncaseb.setOnClickListener(this);
        btncasec.setOnClickListener(this);

        //Tải lên dữ liệu cho câu hỏi từ database
        dataBaseManager = new DataBaseManager(this);
        question = dataBaseManager.getQuestion(2);
        luachon=score;
        getquestion();
    }

    private void getquestion() {
        AsyncTask<Void, Void, Question> asyy = new AsyncTask<Void, Void, Question>() {
            @Override
            protected Question doInBackground(Void... voids) {
                question = dataBaseManager.getQuestion(2);
                return question;
            }

            @Override
            protected void onPostExecute(Question q) {
                if (q.getTrueCase() == 1) {
                    trueCase = R.id.btnCaseA;
                } else if (q.getTrueCase() == 2) {
                    trueCase = R.id.btnCaseB;
                } else if (q.getTrueCase() == 3) {
                    trueCase = R.id.btnCaseC;
                }
                txtquestion.setText("Câu hỏi: " + q.getQuestion());
                btncasea.setText(q.getCaseA());
                btncaseb.setText(q.getCaseB());
                btncasec.setText(q.getCaseC());
                settingCountTime();
                txtscore.setText(score + "");

            }
        };
        asyy.execute();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnCaseA || v.getId() == R.id.btnCaseB || v.getId() == R.id.btnCaseC) {
            switch (v.getId()) {
                case R.id.btnCaseA:
                    v.setBackgroundResource(R.drawable.answer_choose);
                    setCheckPickAnswer(question, v.getId(), btncasea);
                    showAnswer(question);
                    break;
                case R.id.btnCaseB:
                    v.setBackgroundResource(R.drawable.answer_choose);
                    setCheckPickAnswer(question, v.getId(), btncaseb);
                    showAnswer(question);
                    break;
                case R.id.btnCaseC:
                    v.setBackgroundResource(R.drawable.answer_choose);
                    setCheckPickAnswer(question, v.getId(), btncasec);
                    showAnswer(question);
                    break;
            }
        }
        if (v.getId() == trueCase) {
            score += 200;
        } else {
            if (score == 0) {
                score += 0;


            }
            else {
                if (score == 1000) score += 0;
                else if (score == 18000) score += 0;
                else if (score == 24000) score += 0;
                else if(score==luachon){
                    score-=200;
                }
                else {
                    score -= 200;
                }
            }

        }
        txtscore.setText(score + "");
    }

    private void showAnswer(Question question) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (question.getTrueCase() == 1) {
                    trueCase = R.id.btnCaseA;
                    btncasea.setBackgroundResource(R.drawable.answer_true);
                } else if (question.getTrueCase() == 2) {
                    trueCase = R.id.btnCaseB;
                    btncaseb.setBackgroundResource(R.drawable.answer_true);
                } else if (question.getTrueCase() == 3) {
                    trueCase = R.id.btnCaseC;
                    btncasec.setBackgroundResource(R.drawable.answer_true);
                }
            }
        }, 1000);

    }

    private void setCheckPickAnswer(Question question, int checkPickAnswer, Button btn) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkPickAnswer == question.getTrueCase()) {
                    btn.setBackgroundResource(R.drawable.answer_true);
                } else {
                    btn.setBackgroundResource(R.drawable.answer_false);
                }
            }
        }, 1000);

    }

    private void finishQuestion() {

        switch (question.getTrueCase()) {
            case 1:
                btncasea.setBackgroundResource(R.drawable.answer_true);
                break;
            case 2:
                btncaseb.setBackgroundResource(R.drawable.answer_true);
                break;
            case 3:
                btncasec.setBackgroundResource(R.drawable.answer_true);
                break;
        }
        new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                btncasec.setBackgroundResource(R.drawable.botron);
                btncasea.setBackgroundResource(R.drawable.botron);
                btncaseb.setBackgroundResource(R.drawable.botron);
                if(score==luachon&& score!=0){
                    score-=200;
                }
                luachon=score;
                getquestion();
            }
        }.start();
    }

    //        Cài đặt đếm giờ
    private void settingCountTime() {
        final int sec = 11000;
        countDownTimer = new CountDownTimer(sec, 1000) {
            public void onTick(long millisUntilFinished) {
                int minute = (int) (millisUntilFinished / 1000) / 60;
                int second = (int) (millisUntilFinished / 1000) % 60;
                txttime.setText(String.format("%02d", minute) + ":" + String.format("%02d", second));
            }

            @Override
            public void onFinish() {
                finishQuestion();
            }
        };
        countDownTimer.start();
    }

    //Mũi tên trên trang cho phép quay về trang chủ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return true;
    }
}
