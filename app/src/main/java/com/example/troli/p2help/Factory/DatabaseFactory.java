package com.example.troli.p2help.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.troli.p2help.Util.BancoUtil;


public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // cria a tabela sistema
        String sql = "CREATE TABLE "+ BancoUtil.TABELA_SISTEMA+"("
                + BancoUtil.ID_SISTEMA+ " integer primary key autoincrement,"
                + BancoUtil.NOME_SISTEMA + " text,"
                + BancoUtil.VERSAO_SISTEMA + " text"
                +")";
        db.execSQL(sql);

        /* cria a tabela de oferta */
        sql = "CREATE TABLE ofertas("
                + "_id integer primary key autoincrement,"
                + "idsistema integer,"
                + "idcategoria integer,"
                + "usuario integer,"
                + "data text,"
                + "sistema integer,"
                + "titulo text,"
                + "descricao text"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_SISTEMA);
        db.execSQL("DROP TABLE IF EXISTS ofertas");
        db.execSQL("DROP TABLE IF EXISTS OFERTAS");
        onCreate(db);
    }
}
