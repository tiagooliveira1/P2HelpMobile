package com.example.troli.p2help.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.troli.p2help.Factory.DatabaseFactory;
import com.example.troli.p2help.Util.BancoUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Diego on 13/09/2017.
 *
 * Classe para inserção, remoção, atualização e busca em Banco de Dados de um Livro.
 *
 */

public class SistemaDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;


    public SistemaDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long insereDado(Sistema sistema) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_SISTEMA, sistema.getNome());
        valores.put(BancoUtil.VERSAO_SISTEMA, sistema.getVersao());

        resultado = db.insert(BancoUtil.TABELA_SISTEMA, null, valores);
        db.close();

        return resultado;
    }

    public Sistema carregaSistemaPorID(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_SISTEMA, BancoUtil.NOME_SISTEMA, BancoUtil.VERSAO_SISTEMA};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_SISTEMA + " = " + id;

        cursor = db.query(BancoUtil.TABELA_SISTEMA, campos, where, null, null, null, null, null);

        Sistema sistema = new Sistema();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_SISTEMA));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_SISTEMA));
            String versao = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.VERSAO_SISTEMA));

            sistema.setID(ID);
            sistema.setNome(nome);
            sistema.setVersao(versao);
        }
        db.close();
        return sistema;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_SISTEMA, BancoUtil.NOME_SISTEMA, BancoUtil.VERSAO_SISTEMA};
        db = banco.getReadableDatabase();

        //String where = BancoUtil.LIVRO_USUARIO + " = " + id_usuario;
        String where = "";

        cursor = db.query(BancoUtil.TABELA_SISTEMA, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public List<Sistema> carregaDadosLista() {
        Cursor cursor;
        cursor = carregaDados();

        List<Sistema> sistemas = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Sistema sistema = new Sistema();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_SISTEMA));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_SISTEMA));
                    String versao = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.VERSAO_SISTEMA));

                    sistema.setID(ID);

                    sistema.setNome(nome);
                    sistema.setVersao(versao);


                    sistemas.add(sistema);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return sistemas;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_SISTEMA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_SISTEMA,where,null);
        db.close();
    }

    public boolean atualizaLivro(Sistema sistema){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_SISTEMA + " = " + sistema.getID();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_SISTEMA, sistema.getNome());
        valores.put(BancoUtil.VERSAO_SISTEMA, sistema.getVersao());


        int resultado = db.update(BancoUtil.TABELA_SISTEMA,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }
}