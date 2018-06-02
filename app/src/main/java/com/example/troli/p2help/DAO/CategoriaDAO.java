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
public interface CategoriaDAO {

    @Insert
    public long inserir(Categoria categoria);

    @Query("SELECT * from Categoria")
    public List<Categoria> findAll();

    @Query("SELECT * FROM Categoria where ID == :id")
    public Categoria findByID(long id);

    @Delete
    public int excluir(Categoria categoria);

    @Update
    public int editar(Categoria categoria);


}