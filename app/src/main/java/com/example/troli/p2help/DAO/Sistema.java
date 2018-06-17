package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Sistema {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String nome;
    private String versao;

    private int GlobalID; // usado para receber o ID do sistema no webservice

    public Sistema() {

    }

    public Sistema(int ID, String nome, String versao) {
        this.ID = ID;
        this.nome = nome;
        this.versao = versao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public int getGlobalID() {
        return GlobalID;
    }

    public void setGlobalID(int globalID) {
        GlobalID = globalID;
    }

    /* Tive que sobrescrever o método toString para que no spinner apareça somente o nome
        * do sistema, e não o objeto .toString() */
    @Override
    public String toString() {
        return nome;
    }


}
