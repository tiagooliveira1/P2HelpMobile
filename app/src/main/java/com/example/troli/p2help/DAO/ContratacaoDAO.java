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
public interface ContratacaoDAO {

    @Insert
    public long inserir(Contratacao contratacao);

    @Query("SELECT * from Contratacao")
    public List<Contratacao> findAll();

    @Query("SELECT * FROM Contratacao where oferta == :oferta and data = :data and hora = :hora")
    public Contratacao findByID(long oferta, String data, String hora);

    @Delete
    public int excluir(Contratacao contratacao);

    @Update
    public int editar(Contratacao contratacao);

}