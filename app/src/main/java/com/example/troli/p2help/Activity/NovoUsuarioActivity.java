package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.DAO.UsuarioLogado;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class NovoUsuarioActivity extends Activity {

    private EditText editLogin;
    private EditText editNome;
    private EditText editSenha2;
    private EditText editSenha;
    private UsuarioLogado usuarioLogado;

    private FirebaseAuth mAuth;



    private ProgressBar progressDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);


        editLogin = (EditText) findViewById(R.id.editNovoUsuarioLogin);
        editNome = (EditText) findViewById(R.id.editNovoUsuarioNome);

        editSenha = (EditText) findViewById(R.id.editNovoUsuarioSenha1);
        editSenha2 = (EditText) findViewById(R.id.editNovoUsuarioSenha2);

        // se existir usuário logado, então preenche os campos do nome
        usuarioLogado = UsuarioLogado.getInstance();
        AppDatabase app = AppDatabase.getDatabase(this);

        mAuth = FirebaseAuth.getInstance();


        if(usuarioLogado.getID() > 0) {
            Usuario usuario = app.usuarioDAO().findByID(usuarioLogado.getID());
            editNome.setText(usuarioLogado.getNome());
            editLogin.setText(usuario.getLogin());

        }
    }

    public void salvar(View v) {

        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        mAuth.createUserWithEmailAndPassword(login, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    Intent atualizarIntent = new Intent(NovoUsuarioActivity.this,LoginActivity.class);

                    atualizarIntent.putExtra("mensagem_alerta","Usuário criado com sucesso!");
                    atualizarIntent.putExtra("email_cadastrado",task.getResult().getUser().getEmail());
                    startActivity(atualizarIntent);
                } else {
                    Log.e("", "onComplete: Failed=" + task.getException().getMessage());
                    Toast.makeText(NovoUsuarioActivity.this, "Erro ao criar o mizerável."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        // veririca se usuário preencheu a senha
        /*if (editSenha.getText().toString().equals( "" ) ) {
            exibirMensagem("Nenhuma senha foi informada");
            return;
        }
        *//* Verifica se as senhas são iguais *//*
        if (!editSenha.getText().toString().equals( editSenha2.getText().toString() ) ) {
            exibirMensagem("As senhas informadas não são iguais");
        } else {
            Usuario usuario;
            AppDatabase app = AppDatabase.getDatabase(this);
            if(usuarioLogado.getID() > 0) {
                usuario = app.usuarioDAO().findByID(usuarioLogado.getID());
            } else {
                 usuario = new Usuario();
            }

            usuario.setLogin(editLogin.getText().toString().trim());
            usuario.setNome(editNome.getText().toString());
            usuario.setSenha(editSenha.getText().toString());

            long resultado;
            if(usuarioLogado.getID() > 0) {
                resultado = app.usuarioDAO().editar(usuario);
            } else {
                resultado = app.usuarioDAO().inserir(usuario);
            }

            if (resultado > 0) {
                exibirMensagem("Usuário criado com sucesso.");
                Intent atualizarIntent = new Intent(NovoUsuarioActivity.this,LoginActivity.class);
                startActivity(atualizarIntent);
            }
        }*/


    }

    /**
     * Sincroniza o usuário cadastrado com o servidor web
     * @param usuario
     */
    public void syncUsuario(Usuario usuario)
    {

    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
