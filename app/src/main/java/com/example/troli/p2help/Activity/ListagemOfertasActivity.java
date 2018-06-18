package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.troli.p2help.Adapter.OfertaAdapter;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.OfertaDAO;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.R;

import java.util.List;

public class ListagemOfertasActivity extends Activity {

    private ListView listaOfertas;
    private OfertaDAO ofertaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_ofertas);
        carregarElementos();
    }

    public void carregarElementos(){
        AppDatabase app = AppDatabase.getDatabase(this);

        listaOfertas = (ListView) findViewById(R.id.listaOfertas);
        ofertaDAO = app.ofertaDAO();

        List<Oferta> ofertas = ofertaDAO.findAll();

        OfertaAdapter ofertaAdapter = new OfertaAdapter(this,R.layout.item_oferta,ofertas);
        listaOfertas.setAdapter(ofertaAdapter);
        listaOfertas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Oferta oferta = (Oferta)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListagemOfertasActivity.this,AtualizarSistemaActivity.class);
                atualizarIntent.putExtra("ID_OFERTA",oferta.getID());
                startActivity(atualizarIntent);
            }
        });
    }
}
