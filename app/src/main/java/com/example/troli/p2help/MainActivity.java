package com.example.troli.p2help;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.troli.p2help.Activity.CadastrarSistemaActivity;
import com.example.troli.p2help.Activity.ListagemActivity;
import com.example.troli.p2help.Activity.OfertarCursoActivity;
//import com.example.troli.p2help.DAO.ConfigGeralDAO;
//import com.example.troli.p2help.Util.BancoUtil;
import com.facebook.stetho.Stetho;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        // verifica o banco de dados
        //ConfigGeralDAO configGeralDAO = new ConfigGeralDAO(this);
        //configGeralDAO.checkVersion(BancoUtil.VERSAO);
    }

    public void cadastrarSistema(View v){
        Intent intent = new Intent(MainActivity.this,CadastrarSistemaActivity.class);
        startActivity(intent);
    }

    public void acessaListagemSistemas(View v){
        Intent intent = new Intent(MainActivity.this,ListagemActivity.class);
        startActivity(intent);
    }

    public void acessarOfertarCurso(View v) {
        Intent intent = new Intent(MainActivity.this,OfertarCursoActivity.class);
        startActivity(intent);
    }
}
