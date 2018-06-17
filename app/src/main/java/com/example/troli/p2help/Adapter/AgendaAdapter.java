package com.example.troli.p2help.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.troli.p2help.DAO.Agenda;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.R;

import java.util.List;

//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;


public class AgendaAdapter extends ArrayAdapter<Agenda> {

    private int resource;
    private List<Agenda> agendas;


    public AgendaAdapter(Context context, int resource, List<Agenda> objects) {
        super(context, resource, objects);
        this.resource = resource;
        agendas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Agenda agenda = agendas.get(position);


        TextView textData = (TextView) mView.findViewById(R.id.textAgendaData);
        TextView textHora = (TextView) mView.findViewById(R.id.textAgendaHora);

        if(textData != null){
            textData.setText(agenda.getData());
        }
        if(textHora != null){
            textHora.setText(agenda.getHora());
        }
        return mView;
    }
}
