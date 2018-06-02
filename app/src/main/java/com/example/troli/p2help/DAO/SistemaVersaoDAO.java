package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Classe para manipulação do banco
 */
@Dao
public interface SistemaVersaoDAO {

    @Insert
    public long inserir(SistemaVersao sistemaVersao);

    @Query("SELECT * from SistemaVersao")
    public List<SistemaVersao> findAll();

    @Query("SELECT * FROM SistemaVersao where ID == :id")
    public Sistema findByID(long id);

    @Delete
    public int excluir(SistemaVersao sistemaVersao);

    @Update
    public int editar(SistemaVersao sistemaVersao);


}