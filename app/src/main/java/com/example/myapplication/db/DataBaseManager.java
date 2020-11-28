package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseManager {
    private static final String DB_NAME = "thilenlop";
    private String path;
    private Context context;
    private SQLiteDatabase manager;

    private static final String TABLE_NAME = "player";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    public DataBaseManager(Context context){
        this.context = context;
        path = Environment.getDataDirectory().getPath()+"/data/"+context.getPackageName()+"/db";
        new File(path).mkdir();
        path = path+"/"+DB_NAME;
        copyFile();
    }

    private void copyFile(){
        File f = new File(path);
        if (f.exists()){
            return;
        }
        try {
            OutputStream out = new FileOutputStream(path);
            InputStream in = context.getAssets().open(DB_NAME);
            byte b[] = new byte[1024];
            int le = in.read(b);
            while (le >= 0){
                out.write(b, 0, le);
                le = in.read(b);
            }
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabse(){
       if (manager == null || !manager.isOpen()){
           manager= SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
       }
    }

    private void closeDatabse(){
        if(manager != null && manager.isOpen()){
            manager.close();
            manager = null;
        }
    }

    /**
     *
     * @param level from 1-15
     * @return Question
     */

    public Question getQuestion(int level){
        String sql = "SELECT * FROM question WHERE level = "+level+ " ORDER BY RANDOM() limit 1";
        openDatabse();
        Cursor c= manager.rawQuery(sql, null, null);
        c.moveToFirst();
            Question q;
            int id = c.getInt(c.getColumnIndex("_id"));
            String question = c.getString(c.getColumnIndex("question"));
            String caseA = c.getString(c.getColumnIndex("casea"));
            String caseB = c.getString(c.getColumnIndex("caseb"));
            String caseC = c.getString(c.getColumnIndex("casec"));
            int truecase = c.getInt(c.getColumnIndex("truecase"));
            q = new Question();
            q.setId(id);
            q.setLevel(level);
            q.setQuestion(question);
            q.setCaseA(caseA);
            q.setCaseB(caseB);
            q.setCaseC(caseC);
            q.setTrueCase(truecase);
            closeDatabse();
            return q;
    }
    public void insertPlayer(String name, int score){
        openDatabse();
        manager.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("score", score);
        values.put("created_time", new Date().getTime());
        manager.insert("player", null, values);
        manager.setTransactionSuccessful();
        closeDatabse();
    }

//    public void updatePlayer(String name,int score){
//        openDatabse();
//        manager.beginTransaction();
//        ContentValues values=new ContentValues();
//        values.put("name",name);
//        values.put("score",score);
//        manager.update("player",values,null,new String[] {String.valueOf(Player.class)});
//        closeDatabse();
//    }

    public ArrayList<Player> getPlayerHighScore(int limit){
        ArrayList<Player> players = new ArrayList<>();
        openDatabse();
        String sql = "SELECT * FROM player ORDER BY score DESC, createdtime DESC LIMIT " + limit;
        Cursor c = manager.rawQuery(sql, null, null);
        c.moveToFirst();
        int indexId = c.getColumnIndex("id");
        int indexName = c.getColumnIndex("name");
        int indexScore = c.getColumnIndex("score");
        int indexCreatedTime = c.getColumnIndex("createdtime");
        while (!c.isAfterLast()){
            Player p = new Player();
            p.setId(c.getInt(indexId));
            p.setName(c.getString(indexName));
            p.setScore(c.getInt(indexScore));
            p.setCreatedTime(c.getLong(indexCreatedTime));
            players.add(p);
            c.moveToNext();
        }
        c.close();
        closeDatabse();
        return players;
    }

    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_SCORE);
        db.execSQL(create_students_table);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }
}
