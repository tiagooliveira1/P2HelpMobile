package com.example.troli.p2help.Factory;

import android.content.ContentValues;
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
        // cria tabela para guardar configuracao do sistema
        String sql = "CREATE TABLE config_sistema("
                + "_id integer primary key autoincrement,"
                + "versao_bd integer"
                +")";
        db.execSQL(sql);

        ContentValues valores_config = new ContentValues();
        valores_config.put("versao_bd", BancoUtil.VERSAO);

        db.insert("config_sistema",null, valores_config);

        // insere o primeiro registro da configuracao


        /*  cria a tabela de usuarios */
        sql = "CREATE TABLE "+ BancoUtil.TABELA_USUARIO+"("
                + BancoUtil.ID_USUARIO+ " integer primary key autoincrement,"
                + BancoUtil.LOGIN_USUARIO + " text,"
                + BancoUtil.SENHA_USUARIO + " text"
                +")";
        db.execSQL(sql);
        // cria a tabela sistema
        sql = "CREATE TABLE "+ BancoUtil.TABELA_SISTEMA+"("
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
        db.execSQL("DROP TABLE IF EXISTS config_sistema");
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_SISTEMA);
        db.execSQL("DROP TABLE IF EXISTS ofertas");
        db.execSQL("DROP TABLE IF EXISTS OFERTAS");
        //onCreate(db);
    }

    /**
     * Método para verificar se versao do banco setada no app é a mesma já gravada no banco.
     * Caso seja diferente, então executa os drop table
     * @param db
     * @param actualVersion
     */
    public void checkDBVersion(SQLiteDatabase db, int actualVersion) {


    }
}
