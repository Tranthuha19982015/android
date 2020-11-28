package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    MediaPlayer ring = null;
    ImageButton btnnhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnnhac = (ImageButton) findViewById(R.id.ibtnNhac);
        btnnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music();
            }
        });

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

    }

    public void music() {
        if(ring==null) {
            ring = MediaPlayer.create(Settings.this, R.raw.nhac);
            ring.setLooping(true);
            ring.start();
        }
        else {
            ring.stop();
            ring.release();
            ring=null;
        }
    }

    //Mũi tên trên trang cho phép quay về trang chủ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return true;
    }

}
