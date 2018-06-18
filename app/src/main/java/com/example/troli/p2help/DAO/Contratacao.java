package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by troli on 17/06/2018.
 */
@Entity(primaryKeys = {"oferta", "data", "hora", "status"})
public class Contratacao {
    @NonNull
    private int oferta;

    @NonNull
    private String data;

    @NonNull
    private String hora;

    @NonNull
    private String status;

    private int contratante;
    private String data_contratacao;

    public Contratacao() {

    }

    public Contratacao(int oferta, String data, String hora, String status, int contratante, String data_contratacao) {
        this.oferta = oferta;
        this.data = data;
        this.hora = hora;
        this.status = status;
        this.contratante = contratante;
        this.data_contratacao = data_contratacao;
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

    public int getContratante() {
        return contratante;
    }

    public void setContratante(int contratante) {
        this.contratante = contratante;
    }

    public String getData_contratacao() {
        return data_contratacao;
    }

    public void setData_contratacao(String data_contratacao) {
        this.data_contratacao = data_contratacao;
    }
}
