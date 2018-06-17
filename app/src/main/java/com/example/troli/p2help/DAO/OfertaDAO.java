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
public interface OfertaDAO {

    @Insert
    public long inserir(Oferta oferta);

    @Query("SELECT * from Oferta")
    public List<Oferta> findAll();

    @Query("SELECT * FROM Oferta where ID == :id")
    public Oferta findByID(long id);

    @Query("SELECT * FROM Oferta where usuario == :usuario")
    public Oferta findByUsuario(long usuario);

    @Delete
    public int excluir(Oferta oferta);

    @Update
    public int editar(Oferta oferta);


}