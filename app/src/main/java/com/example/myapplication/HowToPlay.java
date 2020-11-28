package com.example.myapplication;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HowToPlay extends AppCompatActivity {
    TextView txtplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtoplay);

        txtplay=(TextView) findViewById(R.id.txtCachChoi);
        //cho phép textview có thể cuộn
        txtplay.setMovementMethod(new ScrollingMovementMethod());

        String kq="     Khi người chơi nhấn vào 'Thi lên lớp' thì sẽ có câu hỏi hiện ra màn hình" +
                " và các đáp án của nó. Người chơi sẽ phải chọn 1 trong 3 đáp án đúng trong" +
                " khoảng thời gian 10s. Nếu quá 10s người chơi chưa trả lời được câu hỏi " +
                "thì đáp án đúng sẽ được hiển thị. Nếu trả lời đúng thì người chơi sẽ được lên một lớp. " +
                "Còn nếu trả lời sai thì người chơi sẽ bị tụt xuống một lớp. Trong trường hợp người chơi đang " +
                "ở lớp cuối cùng của cấp đó thì người chơi sẽ không bị giảm lớp hiện tại.";
        txtplay.setText(kq);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return true;
    }
}
