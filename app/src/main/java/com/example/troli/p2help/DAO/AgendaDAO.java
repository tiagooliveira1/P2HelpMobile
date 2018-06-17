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
public interface AgendaDAO {

    @Insert
    public long inserir(Agenda agenda);

    @Query("SELECT * from Agenda")
    public List<Agenda> findAll();

    @Query("SELECT * FROM Agenda where ID == :id")
    public Agenda findByID(long id);

    @Delete
    public int excluir(Agenda agenda);

    @Update
    public int editar(Agenda agenda);

}