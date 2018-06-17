package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.R;

public class NovoUsuarioActivity extends Activity {

    private EditText editLogin;
    private EditText editNome;
    private EditText editSenha2;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        editLogin = (EditText) findViewById(R.id.editNovoUsuarioLogin);
        editNome = (EditText) findViewById(R.id.editNovoUsuarioNome);

        editSenha = (EditText) findViewById(R.id.editNovoUsuarioSenha1);
        editSenha2 = (EditText) findViewById(R.id.editNovoUsuarioSenha2);
    }

    public void salvar(View v) {

        /* Verifica se as senhas são iguais */
        if (!editSenha.getText().toString().equals( editSenha2.getText().toString() ) ) {
            exibirMensagem("As senhas informadas não são iguais");
        } else {
            AppDatabase app = AppDatabase.getDatabase(this);
            Usuario usuario = new Usuario();
            usuario.setLogin(editLogin.getText().toString().trim());
            usuario.setNome(editNome.getText().toString());
            usuario.setSenha(editSenha.getText().toString());

            long resultado = app.usuarioDAO().inserir(usuario);
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
