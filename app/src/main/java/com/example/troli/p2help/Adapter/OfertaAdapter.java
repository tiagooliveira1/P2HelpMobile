package com.example.troli.p2help.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.troli.p2help.Activity.OfertarCursoActivity;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.R;

import java.util.List;

//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;


public class OfertaAdapter extends ArrayAdapter<Oferta> {

    private int resource;
    private List<Oferta> ofertas;

    public OfertaAdapter(Context context, int resource, List<Oferta> objects) {
        super(context, resource, objects);
        this.resource = resource;
        ofertas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Oferta oferta = ofertas.get(position);
        AppDatabase app = AppDatabase.getDatabase(getContext());

        Usuario usuario = app.usuarioDAO().findByID(oferta.getUsuario());
        Sistema sistema = app.sistemaDAO().findByID(oferta.getSistema());

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textTitulo = (TextView) mView.findViewById(R.id.txtListaOfertaTitulo);
        TextView textDescricao = (TextView) mView.findViewById(R.id.txtListaOfertaDescricao);
        TextView textUsuario = (TextView) mView.findViewById(R.id.txtListaOfertaUsuario);
        TextView textValorHora = (TextView) mView.findViewById(R.id.txtListaOfertaValorHora);

        if(textID != null){
            textID.setText(String.valueOf(oferta.getID()));
        }
        // imprime o titulo da oferta junto com o nome do sistema
        if(textTitulo != null){
            textTitulo.setText(sistema.getNome() + " - " + oferta.getTitulo());
        }
        if(textDescricao != null){
            textDescricao.setText(oferta.getDescricao());
        }
        if(textUsuario != null){
            textUsuario.setText("Usuário: "+usuario.getNome());
        }
        if(textValorHora != null){
            textValorHora.setText("Valor da Hora: R$ "+String.valueOf(oferta.getValor_hora()));
        }
        return mView;
    }
}
