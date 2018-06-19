package com.example.troli.p2help.DAO;

/**
 * Created by troli on 17/06/2018.
 * usando singleton para guardar o usuário logado
 */

public class UsuarioLogado {
    private static UsuarioLogado instance = new UsuarioLogado();

    private String nome;
    private int ID;

    public UsuarioLogado() {
        this.ID = -1;
    }

    public UsuarioLogado(int ID, String nome ) {
        this.nome = nome;
        this.ID = ID;
    }

    public static UsuarioLogado getInstance() {
        return instance;
    }

    public static void setInstance(UsuarioLogado instance) {
        UsuarioLogado.instance = instance;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
