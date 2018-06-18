package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.troli.p2help.Adapter.MinhaOfertaAdapter;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.UsuarioLogado;
import com.example.troli.p2help.R;

import java.util.ArrayList;
import java.util.List;

public class MinhasOfertasActivity extends Activity {

    List<Oferta> listaOfertas;
    UsuarioLogado usuarioLogado;
    private ListView listViewMinhasOfertas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_ofertas);

        listViewMinhasOfertas = (ListView) findViewById(R.id.listViewMinhasOfertas);

        usuarioLogado = UsuarioLogado.getInstance();
        AppDatabase app = AppDatabase.getDatabase(this);
        listaOfertas = app.ofertaDAO().findByUsuario(usuarioLogado.getID());

        MinhaOfertaAdapter agendaContratacaoAdapter = new MinhaOfertaAdapter(this, R.layout.item_minha_oferta,listaOfertas);
        listViewMinhasOfertas.setAdapter(agendaContratacaoAdapter);
        listViewMinhasOfertas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Oferta oferta = (Oferta)parent.getItemAtPosition(position);
                Intent detalheIntent = new Intent(MinhasOfertasActivity.this,OfertarCursoActivity.class);
                detalheIntent.putExtra("ID_OFERTA_EDIT",oferta.getID());
                startActivity(detalheIntent);
            }
        });


    }
}
