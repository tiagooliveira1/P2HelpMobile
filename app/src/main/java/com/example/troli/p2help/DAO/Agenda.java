package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Agenda {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String nome;
    private int tipo_solicitacao;
    private float valor_hora;
    private String titulo;
    private String descricao;
    private int usuario;
    private int idcategoria;
    private String status;
    private int Sistema;

    public Agenda() {

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

    public int getTipo_solicitacao() {
        return tipo_solicitacao;
    }

    public void setTipo_solicitacao(int tipo_solicitacao) {
        this.tipo_solicitacao = tipo_solicitacao;
    }

    public float getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(float valor_hora) {
        this.valor_hora = valor_hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSistema() {
        return Sistema;
    }

    public void setSistema(int sistema) {
        Sistema = sistema;
    }
}
