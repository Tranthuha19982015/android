package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DataBaseManager;
import com.example.myapplication.db.Player;

import java.util.ArrayList;

public class TopRankings extends AppCompatActivity implements View.OnClickListener {
    ListView lvxephang;
    ArrayList<Player> player=null;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toprankings);

        findViewById(R.id.btn_back).setOnClickListener(this);

        dataBaseManager=new DataBaseManager(this);
        player=new ArrayList<>();

        loadTopRankings();
    }

    public void loadTopRankings() {
        lvxephang = (ListView) findViewById(R.id.lvTop);
        player = dataBaseManager.getPlayerHighScore(10);
        TopRangeAdapter customer = new TopRangeAdapter(player);
        lvxephang.setAdapter(customer);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
