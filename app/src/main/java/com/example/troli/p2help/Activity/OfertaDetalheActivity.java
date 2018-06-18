package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.troli.p2help.Adapter.AgendaContratacaoAdapter;
import com.example.troli.p2help.Adapter.OfertaAdapter;
import com.example.troli.p2help.DAO.Agenda;
import com.example.troli.p2help.DAO.AgendaDAO;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.OfertaDAO;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.R;

import java.util.List;

public class OfertaDetalheActivity extends Activity {

    private AppDatabase app;
    private TextView txtOfertaDetalheTitulo;
    private TextView txtOfertaDetalheDescricao;
    private TextView txtOfertaDetalheSistema;
    private TextView txtOfertaDetalheValorHora;
    private TextView txtOfertaDetalheUsuário;

    private ListView listaAgendas;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_detalhe);

        app = AppDatabase.getDatabase(this);
        bundle = getIntent().getExtras();
        Oferta oferta = app.ofertaDAO().findByID(bundle.getInt("ID_OFERTA"));
        Sistema sistema = app.sistemaDAO().findByID(oferta.getSistema());
        Usuario usuario = app.usuarioDAO().findByID(oferta.getUsuario());

        txtOfertaDetalheTitulo = (TextView) findViewById(R.id.txtOfertaDetalheTitulo);
        txtOfertaDetalheDescricao = (TextView) findViewById(R.id.txtOfertaDetalheDescricao);
        txtOfertaDetalheSistema = (TextView) findViewById(R.id.txtOfertaDetalheSistema);
        txtOfertaDetalheValorHora = (TextView) findViewById(R.id.txtOfertaDetalheValorHora);
        txtOfertaDetalheUsuário = (TextView) findViewById(R.id.txtOfertaDetalheUsuário);


        txtOfertaDetalheTitulo.setText(oferta.getTitulo());
        txtOfertaDetalheDescricao.setText(oferta.getDescricao());
        txtOfertaDetalheSistema.setText(sistema.getNome());
        txtOfertaDetalheValorHora.setText(String.valueOf(oferta.getValor_hora()));
        txtOfertaDetalheUsuário.setText(usuario.getNome());

        carregarElementos();

        //detalheIntent.putExtra("ID_OFERTA",oferta.getID());
    }

    public void carregarElementos(){
        AppDatabase app = AppDatabase.getDatabase(this);

        listaAgendas = (ListView) findViewById(R.id.listaContratacaoAgenda);
        AgendaDAO agendaDAO = app.agendaDAO();


        List<Agenda> agendas = agendaDAO.findByOfertaID(bundle.getInt("ID_OFERTA"));

        AgendaContratacaoAdapter agendaContratacaoAdapter = new AgendaContratacaoAdapter(this, R.layout.layout_agenda_contratacao,agendas);
        listaAgendas.setAdapter(agendaContratacaoAdapter);
        listaAgendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Oferta oferta = (Oferta)parent.getItemAtPosition(position);
                Intent detalheIntent = new Intent(OfertaDetalheActivity.this,OfertaDetalheActivity.class);
                detalheIntent.putExtra("ID_OFERTA",oferta.getID());
                startActivity(detalheIntent);
            }
        });
    }
}
