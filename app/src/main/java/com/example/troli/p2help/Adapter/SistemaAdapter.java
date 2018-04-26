package com.example.troli.p2help.Adapter;

import android.content.Context;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.R;

import java.util.List;



public class SistemaAdapter extends ArrayAdapter<Sistema> {

    private int resource;
    private List<Sistema> sistemas;

    public SistemaAdapter(Context context,  int resource, List<Sistema> objects) {
        super(context, resource, objects);
        this.resource = resource;
        sistemas = objects;
    }
  /*  public SistemaAdapter(Context context, @LayoutRes int resource, List<Sistema> objects) {
        super(context, resource, objects);
        this.resource = resource;
        sistemas = objects;
    }
*/
    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Sistema sistema = sistemas.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textTitulo = (TextView) mView.findViewById(R.id.textNome);
        TextView textGenero = (TextView) mView.findViewById(R.id.textVersao);

        if(textID != null){
            textID.setText(String.valueOf(sistema.getID()));
        }
        if(textTitulo != null){
            textTitulo.setText(sistema.getNome());
        }
        if(textGenero != null){
            textGenero.setText(sistema.getVersao());
        }
        return mView;
    }
}
