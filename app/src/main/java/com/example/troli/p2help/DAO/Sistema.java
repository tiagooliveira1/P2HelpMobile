package com.example.troli.p2help.DAO;


public class Sistema {
    private int ID;
    private String nome;
    private String versao;

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


}
