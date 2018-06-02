package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Diego on 08/11/2017.
 */
@Entity
public class Categoria {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String descricao;
    private int categoria_mae;



    public Categoria() {

    }

    public Categoria(String descricao, int categoria_mae) {
        this.descricao = descricao;
        this.categoria_mae = categoria_mae;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCategoria_mae() {
        return categoria_mae;
    }

    public void setCategoria_mae(int categoria_mae) {
        this.categoria_mae = categoria_mae;
    }
}
