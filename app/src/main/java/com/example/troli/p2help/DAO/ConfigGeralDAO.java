package com.example.troli.p2help.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.troli.p2help.Factory.DatabaseFactory;
import com.example.troli.p2help.Util.BancoUtil;

/**
 * Created by troli on 26/04/2018.
 */

public class ConfigGeralDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public ConfigGeralDAO(Context context) {
        banco = new DatabaseFactory(context);

        //banco.onUpgrade(banco.getWritableDatabase(), 4, 6);
    }

    public void checkVersion(int actualVersion) {
        ContentValues valores;
        Cursor cursor;
        String[] campos = {"versao_bd"};
        db = banco.getReadableDatabase();
        String where = "";

        cursor = db.query("config_sistema", campos,"", null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if( cursor.getColumnIndexOrThrow("versao_bd") != BancoUtil.VERSAO) {
            banco.onUpgrade(banco.getWritableDatabase(), 1,BancoUtil.VERSAO);
        }
        db.close();

    }
}
