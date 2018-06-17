package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.troli.p2help.Adapter.AgendaAdapter;
import com.example.troli.p2help.DAO.Agenda;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Categoria;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.SistemaDAO;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;

import java.util.ArrayList;
import java.util.List;

public class OfertarCursoActivity extends Activity {

    //private String[] listaSistemas = new String[4];

    private Spinner spinnerTipoOferta;
    private AutoCompleteTextView autoCompleteSistema;

    private TextView editValorHora;
    private TextView editTitulo;
    private TextView editDescricao;
    private ListView listViewHorarios;

    ArrayList<Categoria> listaCategorias;
    ArrayList<Sistema> listaSistemas;

    private AgendaAdapter myAdapter;
    ArrayList<Agenda> listaHorarios = new ArrayList<Agenda>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertar_curso);

        editValorHora = (TextView) findViewById(R.id.editOfertaAnuncioValorHora);
        editTitulo = (TextView) findViewById(R.id.editOfertaAnuncioTitulo);
        editDescricao = (TextView) findViewById(R.id.editOfertaAnuncioDescricao);
        listViewHorarios = (ListView) findViewById(R.id.listHorarios);

        /* Configura autocomplete do sistema */
        listaSistemas = getListaSistemas();
        ArrayAdapter<Sistema> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listaSistemas);

        autoCompleteSistema = (AutoCompleteTextView) findViewById(R.id.acSistema);
        autoCompleteSistema.setAdapter(adapter);

        autoCompleteSistema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Sistema sistema = (Sistema) arg0.getAdapter().getItem(arg2);
                Toast.makeText(OfertarCursoActivity.this, "Selections ."+sistema.getNome(), Toast.LENGTH_SHORT).show();

            }
        });


        listaHorarios.add(new Agenda("31/12/2018", "09:00", "A"));
        listaHorarios.add(new Agenda("31/12/2018", "15:00", "A"));

        //ArrayAdapter<Agenda> adapterAgenda = new ArrayAdapter<>(this, R.layout.layout_horarios, listaHorarios);
        //listViewHorarios.setAdapter(adapterAgenda);

        myAdapter = new AgendaAdapter(this,R.layout.layout_horarios,listaHorarios);
        listViewHorarios.setAdapter(myAdapter);

        //listaHorarios.add(new Agenda("15/12/2018", "13:00", "A"));

        /* Configura spinner das categorias */
        listaCategorias = getCategorias();
        ArrayAdapter<Categoria> adapterTipoOferta = new ArrayAdapter<>(this, R.layout.layout_spinner_ofertas, listaCategorias);
        spinnerTipoOferta = (Spinner) findViewById(R.id.spinnerTipoOfertaCurso);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoOferta.setAdapter(adapterTipoOferta);

        spinnerTipoOferta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Categoria categoria = (Categoria) spinnerTipoOferta.getSelectedItem();
                Toast.makeText(OfertarCursoActivity.this, "Seleção: "+categoria.getDescricao(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(OfertarCursoActivity.this, "Selections cleared.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void addHorario(View v)
    {
        listaHorarios.add(new Agenda("15/12/2018", "13:00", "A"));
        myAdapter.notifyDataSetChanged();
    }
    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    private ArrayList<Sistema> getListaSistemas() {

        AppDatabase app = AppDatabase.getDatabase(this);
        List<Sistema> sistemas = app.sistemaDAO().findAll();
        return new ArrayList<Sistema>(sistemas);
    }

    /**
     * retorna todas as categorias, e já converte para arraylist, para conseguir utilizar o
     * arrayAdapter com spinner
     * @return
     */
    private ArrayList<Categoria> getCategorias()
    {
        AppDatabase app = AppDatabase.getDatabase(this);
        List<Categoria> categorias = app.categoriaDAO().findAll();
        return new ArrayList<Categoria>(categorias);
    }


    public void salvar(View v)
    {
        AppDatabase app = AppDatabase.getDatabase(this);

        // verifica se o sistema informado textualmente já existe de fato
        Sistema sistema = app.sistemaDAO().findByName(autoCompleteSistema.getText().toString());
        if(sistema == null) {
            // se sistema não existir, então insere
            sistema = new Sistema();
            sistema.setNome(autoCompleteSistema.getText().toString());
            app.sistemaDAO().inserir(sistema);
        }


        Oferta oferta = new Oferta();

        oferta.setIdcategoria(1);
        oferta.setDescricao(editDescricao.getText().toString());
        oferta.setTitulo(editTitulo.getText().toString());
        oferta.setValor_hora(Float.parseFloat(editValorHora.getText().toString()));
        oferta.setStatus("P");
        oferta.setSistema(sistema.getID());

        long resultado = app.ofertaDAO().inserir(oferta);
        if(resultado > 0){
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OfertarCursoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Erro ao cadastrar oferta!", Toast.LENGTH_SHORT).show();
        }

    }

}
