package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.SistemaDAO;
import com.example.troli.p2help.R;

import java.util.List;

public class OfertarCursoActivity extends Activity {

    private String[] listaSistemas = new String[4];

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

}
