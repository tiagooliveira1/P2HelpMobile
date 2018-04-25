package com.example.troli.p2help.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.troli.p2help.Util.BancoUtil;


/**
 * Created by Diego on 13/09/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ BancoUtil.TABELA_SISTEMA+"("
                + BancoUtil.ID_SISTEMA+ " integer primary key autoincrement,"
                + BancoUtil.NOME_SISTEMA + " text,"
                + BancoUtil.VERSAO_SISTEMA + " text"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_SISTEMA);
        onCreate(db);
    }
}
