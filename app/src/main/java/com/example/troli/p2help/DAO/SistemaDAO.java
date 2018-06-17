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

import java.util.ArrayList;
import java.util.List;


/**
 * Classe para manipulação do banco
 */
@Dao
public interface SistemaDAO {

    @Insert
    public long inserir(Sistema sistema);

    @Query("SELECT * from Sistema")
    public List<Sistema> findAll();

    @Query("SELECT * FROM Sistema where ID == :id")
    public Sistema findByID(long id);

    @Query("SELECT * FROM Sistema where nome == :nome")
    public Sistema findByName(String nome);

    @Delete
    public int excluir(Sistema sistema);

    @Update
    public int editar(Sistema sistema);


}