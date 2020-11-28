package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.db.DataBaseManager;
import com.example.myapplication.db.Question;

import java.sql.DatabaseMetaData;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnthi, btncap, btnxephang, btnthoat;
//    MediaPlayer ring = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ring = MediaPlayer.create(this, R.raw.nhac);
//        ring.start();

        //Nút thi lên lớp
        btnthi = (Button) findViewById(R.id.btnThi);
        btnthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exam();
            }
        });

        //Nút cấp hiện tại
        btncap = (Button) findViewById(R.id.btnCapHT);
        btncap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level();
            }
        });

        //Nút bảng xếp hạng
        btnxephang = (Button) findViewById(R.id.btnBangXH);
        btnxephang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenBangXepHang();
            }
        });

        //Nút thoát
        btnthoat = (Button) findViewById(R.id.btnThoat);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có muốn thoát game?");
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
            }
        });
    }

    private void level() {
        Intent capdo = new Intent(this, CurentLevel.class);
        startActivity(capdo);
    }

    private void exam() {
        Intent thi = new Intent(this, ExamToClass.class);
        startActivity(thi);
    }

    private void doOpenBangXepHang() {
        Intent xephang = new Intent(this, TopRankings.class);
        startActivity(xephang);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

//        View actionView = menu.getItem(1).getActionView();
//        View viewFromMyLayout = actionView.findViewById(R.id.huong_dan);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cai_dat:
                doSetting();
                return true;
            case R.id.huong_dan:
                howtoPlay();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void howtoPlay() {
        Intent play = new Intent(this, HowToPlay.class);
        startActivity(play);
    }

    private void doSetting() {
        Intent set = new Intent(this, Settings.class);
        startActivity(set);
    }
}