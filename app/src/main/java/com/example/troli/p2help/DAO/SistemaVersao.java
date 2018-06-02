package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class SistemaVersao {
    @PrimaryKey(autoGenerate = false)
    private int ID;
    @PrimaryKey(autoGenerate = false)
    private String versao;

    public SistemaVersao() {

    }

    public SistemaVersao(int ID, String versao) {
        this.ID = ID;
        this.versao = versao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
