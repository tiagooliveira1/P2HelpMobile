package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.troli.p2help.Adapter.MinhaOfertaAdapter;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.UsuarioLogado;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MinhasOfertasActivity extends AppCompatActivity {

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
        registerForContextMenu(listViewMinhasOfertas);
        //listViewMinhasOfertas.setOnCreateContextMenuListener();


    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view,
                                    ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        getMenuInflater().inflate(
                R.menu.menu_contexto_oferta, contextMenu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        final Oferta oferta = (Oferta) listViewMinhasOfertas.getItemAtPosition(info.position);

        switch (item.getItemId()) {
            case R.id.opcao1:
                Intent detalheIntent = new Intent(MinhasOfertasActivity.this,OfertarCursoActivity.class);
                detalheIntent.putExtra("ID_OFERTA_EDIT",oferta.getID());
                startActivity(detalheIntent);
                return true;
            case R.id.opcao2:
                AlertDialog.Builder alert = new AlertDialog.Builder(MinhasOfertasActivity.this);
                alert.setTitle("Exclusão");
                alert.setMessage("Você tem certeza que deseja excluir a oferta?");
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase app = AppDatabase.getDatabase(MinhasOfertasActivity.this);
                        app.ofertaDAO().excluir(oferta);
                        Toast.makeText(MinhasOfertasActivity.this, "Oferta excluída com sucesso.",
                                Toast.LENGTH_SHORT).show();
                        Intent principal = new Intent(MinhasOfertasActivity.this,MainActivity.class);
                        startActivity(principal);
                    }
                });
                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
                //return true;

        }
        return super.onContextItemSelected(item);
    }

}
