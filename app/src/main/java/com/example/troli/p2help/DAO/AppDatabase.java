package com.example.troli.p2help.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by troli on 02/05/2018.
 */
@Database(entities = {
        Sistema.class,
        Usuario.class,
        Categoria.class,
        SistemaVersao.class,
        Oferta.class,
        Agenda.class,
        Contratacao.class

}, version =25 )
public abstract class AppDatabase extends RoomDatabase {

    public abstract SistemaDAO sistemaDAO();
    public abstract UsuarioDAO usuarioDAO();
    public abstract CategoriaDAO categoriaDAO();
    public abstract OfertaDAO ofertaDAO();
    public abstract AgendaDAO agendaDAO();
    public abstract ContratacaoDAO contratacaoDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if(INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "p2db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            //INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "p2db").fallbackToDestructiveMigration().build();
            //INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "p2db").build();
        return INSTANCE;
    }
}
