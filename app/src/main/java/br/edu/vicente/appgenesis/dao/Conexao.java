package br.edu.vicente.appgenesis.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexao extends SQLiteOpenHelper {
    private static final String name = "calculo.db";
    private static final int version = 1;

    public Conexao(Context context){
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL("create table calculo(" +
                   "id integer primary key autoincrement," +
                   "tensao varchar(10),"+
                   "corrente varchar(10),"+
                   "distancia varchar(10),"+
                   "potencia varchar(10),"+
                   "disjuntor varchar(10),"+
                   "queda varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
