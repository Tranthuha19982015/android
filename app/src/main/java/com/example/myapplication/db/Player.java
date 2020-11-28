package com.example.myapplication.db;

public class Player {
    private int id;
    private String name;
    private int score;
    private long createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String Levels(){
        String level="";
        if(score < 3000)
            level="Mầm non";
        else if(score >= 3000 && score < 6000)
            level="Cấp 1";
        else if(score >= 6000 && score < 9000)
            level="Cấp 2";
        else if(score >= 9000 && score < 12000)
            level="Cấp 3";
        else if(score >= 12000)
            level="Trường đời";
        return level;
    }

    @Override
    public String toString() {
        return id + name + score + Levels();
    }
}
