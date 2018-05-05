package com.example.troli.p2help.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.troli.p2help.Factory.DatabaseFactory;
import com.example.troli.p2help.Util.BancoUtil;


/**
 * Created by Diego on 08/11/2017.
 */

public class UsuarioDAO {

    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public static final int LIVROS_TOTAL = 1;
    public static final int LIVROS_FAVORITOS = 2;


    public UsuarioDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long insereDado(Usuario usuario) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.LOGIN_USUARIO, usuario.getLogin());
        valores.put(BancoUtil.SENHA_USUARIO, usuario.getSenha());

        resultado = db.insert(BancoUtil.TABELA_USUARIO, null, valores);
        db.close();

        return resultado;

    }

    public Usuario carregaUsuarioPorID(long id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_USUARIO, BancoUtil.LOGIN_USUARIO, BancoUtil.SENHA_USUARIO};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_USUARIO + " = " + id;

        cursor = db.query(BancoUtil.TABELA_USUARIO, campos, where, null, null, null, null, null);

        Usuario usuario = new Usuario();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_USUARIO));
            String login = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.LOGIN_USUARIO));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA_USUARIO));

            usuario.setID(ID);
            usuario.setLogin(login);
            usuario.setSenha(senha);

        }
        db.close();
        return usuario;
    }

    public long validaUsuario(String login, String senha){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_USUARIO, BancoUtil.LOGIN_USUARIO, BancoUtil.SENHA_USUARIO};
        db = banco.getReadableDatabase();

        String where = BancoUtil.LOGIN_USUARIO + " = " + "'" + login + "'";
        where += " and " + BancoUtil.SENHA_USUARIO + " = " + "'" + senha + "'";

        cursor = db.query(BancoUtil.TABELA_USUARIO, campos, where, null, null, null, null, null);

        cursor.moveToFirst();

        db.close();

        if(cursor.getCount() > 0)
            return cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_USUARIO));
        return -1;
    }
}
