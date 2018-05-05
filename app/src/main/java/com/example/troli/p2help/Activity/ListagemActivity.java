package com.example.troli.p2help.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.troli.p2help.Adapter.SistemaAdapter;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.SistemaDAO;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;

import java.util.List;

public class ListagemActivity extends Activity {

    private ListView listaSistemas;
    private SistemaAdapter myAdapter;
    SistemaDAO sistemaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        carregarElementos();
    }

    public void carregarElementos(){
        AppDatabase app = AppDatabase.getDatabase(this);

        listaSistemas = (ListView) findViewById(R.id.listSistemas);
        sistemaDAO = app.sistemaDAO();

        List<Sistema> sistemas = sistemaDAO.findAll();

        myAdapter = new SistemaAdapter(this,R.layout.item_sistema,sistemas);
        listaSistemas.setAdapter(myAdapter);
        listaSistemas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sistema sistema = (Sistema)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListagemActivity.this,AtualizarSistemaActivity.class);
                atualizarIntent.putExtra("ID_LIVRO",sistema.getID());
                startActivity(atualizarIntent);
            }
        });
    }



}

