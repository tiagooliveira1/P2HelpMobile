package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Agenda {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    private int oferta;
    private String data;
    private String hora;
    private String status;

    public Agenda() {

    }

    public Agenda(int ID, int oferta, String data, String hora, String status) {
        this.ID = ID;
        this.oferta = oferta;
        this.data = data;
        this.hora = hora;
        this.status = status;
    }

    public Agenda(String data, String hora, String status) {
        this.data = data;
        this.hora = hora;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        this.oferta = oferta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
