package com.example.troli.p2help.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.R;

import java.util.List;

//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;


public class MinhaOfertaAdapter extends ArrayAdapter<Oferta> {

    private int resource;
    private List<Oferta> ofertas;

    public MinhaOfertaAdapter(Context context, int resource, List<Oferta> objects) {
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

        Sistema sistema = app.sistemaDAO().findByID(oferta.getSistema());

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textTitulo = (TextView) mView.findViewById(R.id.txtListaMinhaOfertaTitulo);
        TextView textDescricao = (TextView) mView.findViewById(R.id.txtListaMinhaOfertaDescricao);
        TextView textSistema = (TextView) mView.findViewById(R.id.txtListaMinhaOfertaSistema);
        TextView textValorHora = (TextView) mView.findViewById(R.id.txtListaMinhaOfertaValorHora);

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
        if(textSistema != null){
            textSistema.setText("Sistema: "+sistema.getNome());
        }
        if(textValorHora != null){
            textValorHora.setText("Valor da Hora: R$ "+String.valueOf(oferta.getValor_hora()));
        }
        return mView;
    }
}
