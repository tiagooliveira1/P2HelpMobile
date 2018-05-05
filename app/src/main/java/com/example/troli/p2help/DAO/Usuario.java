package com.example.troli.p2help.DAO;

/**
 * Created by Diego on 08/11/2017.
 */

public class Usuario {
    private int ID;
    private String login;
    private String senha;

    public Usuario() {
    }

    public Usuario(int ID, String login, String senha) {
        this.ID = ID;
        this.login = login;
        this.senha = senha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
