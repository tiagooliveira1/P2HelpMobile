package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Categoria;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.SistemaDAO;
import com.example.troli.p2help.R;

import java.util.ArrayList;
import java.util.List;

public class OfertarCursoActivity extends Activity {

    private String[] listaSistemas = new String[4];

    private Spinner spinnerTipoOferta;

    ArrayList<Categoria> listaCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertar_curso);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listaSistemas);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.acTexto);
        textView.setAdapter(adapter);
        getListaSistemas();

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

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    private String[] getListaSistemas() {

        AppDatabase app = AppDatabase.getDatabase(this);
        List<Sistema> sistemas = app.sistemaDAO().findAll();
        int i = 0;
        for (Sistema sistema : sistemas) {
            listaSistemas[i] = sistema.getNome();
            i++;
        }

        return listaSistemas;
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

}
