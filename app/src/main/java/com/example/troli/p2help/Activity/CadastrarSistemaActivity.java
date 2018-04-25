package com.example.troli.p2help.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.SistemaDAO;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;

public class CadastrarSistemaActivity extends Activity {

    private EditText editNomeSistema;
    private EditText editVersaoSistema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_sistema);

        editNomeSistema = (EditText) findViewById(R.id.editNomeSistema);
        editVersaoSistema = (EditText) findViewById(R.id.editVersaoSistema);
    }

    public void salvarSistema(View v){
        SistemaDAO sistemaDAO = new SistemaDAO(this);
        Sistema sistema = new Sistema();
        sistema.setNome(editNomeSistema.getText().toString());
        sistema.setVersao(editVersaoSistema.getText().toString());

        long resultado = sistemaDAO.insereDado(sistema);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarSistemas = new Intent(CadastrarSistemaActivity.this,MainActivity.class);
            startActivity(listarSistemas);
            finish();
        }
        else{
            exibirMensagem("Erro ao cadastrar o item.");
        }
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

}
