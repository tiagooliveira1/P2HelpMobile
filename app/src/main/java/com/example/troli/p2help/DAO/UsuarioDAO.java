package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.troli.p2help.Factory.DatabaseFactory;
import com.example.troli.p2help.Util.BancoUtil;

import java.util.List;


/**
 * Created by Diego on 08/11/2017.
 */

@Dao
public interface UsuarioDAO {

    @Insert
    public long inserir(Usuario usuario);

    @Query("SELECT * from Usuario")
    public List<Usuario> findAll();

    @Query("SELECT * FROM Usuario where ID == :id")
    public Usuario findByID(long id);

    @Query("SELECT * FROM Usuario where login == :login and senha = :senha")
    public Usuario findByLoginAndPassword(String login, String senha );

    @Delete
    public int excluir(Usuario usuario);

    @Update
    public int editar(Usuario usuario);

}
