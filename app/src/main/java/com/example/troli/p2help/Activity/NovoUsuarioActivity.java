package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.DAO.UsuarioLogado;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;

public class NovoUsuarioActivity extends Activity {

    private EditText editLogin;
    private EditText editNome;
    private EditText editSenha2;
    private EditText editSenha;
    private UsuarioLogado usuarioLogado;

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

        if(usuarioLogado.getID() > 0) {
            Usuario usuario = app.usuarioDAO().findByID(usuarioLogado.getID());
            editNome.setText(usuarioLogado.getNome());
            editLogin.setText(usuario.getLogin());

        }
    }

    public void salvar(View v) {

        // veririca se usuário preencheu a senha
        if (editSenha.getText().toString().equals( "" ) ) {
            exibirMensagem("Nenhuma senha foi informada");
            return;
        }
        /* Verifica se as senhas são iguais */
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
        }
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
