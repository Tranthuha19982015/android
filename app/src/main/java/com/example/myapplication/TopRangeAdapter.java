package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.db.Player;

import java.util.ArrayList;

public class TopRangeAdapter extends BaseAdapter {
    private ArrayList<Player> players;
    public TopRangeAdapter(ArrayList<Player> players){
        this.players = players;
    }


    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Player getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_range, parent, false);
        TextView tvNumber = itemView.findViewById(R.id.txtNumber);
        TextView tvName = itemView.findViewById(R.id.txtName);
        TextView tvScore = itemView.findViewById(R.id.txtScore);
        TextView tvLevels=itemView.findViewById(R.id.txtLevels);
        tvNumber.setText((position+1)+"");
        tvName.setText(players.get(position).getName());
        tvScore.setText(players.get(position).getScore()+"");
        tvLevels.setText(players.get(position).Levels());
        return itemView;
    }
}
