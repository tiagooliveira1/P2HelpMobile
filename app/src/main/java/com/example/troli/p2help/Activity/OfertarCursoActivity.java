package com.example.troli.p2help.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.troli.p2help.Adapter.AgendaAdapter;
import com.example.troli.p2help.DAO.Agenda;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Categoria;
import com.example.troli.p2help.DAO.Oferta;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.SistemaDAO;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.DAO.UsuarioLogado;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.support.v7.app.AppCompatActivity;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

public class OfertarCursoActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener,
        DialogInterface.OnCancelListener {

    AppDatabase app = AppDatabase.getDatabase(this);

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

    // variáveis para inserir a data da agenda com datepicker
    private int year, month, day, hour, minute;


    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertar_curso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_my_calendar);

        bundle = getIntent().getExtras();
        Oferta ofertaEdit;

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
                Toast.makeText(OfertarCursoActivity.this, "Selections ." + sistema.getNome(), Toast.LENGTH_SHORT).show();

            }
        });


        listaHorarios.add(new Agenda("31/12/2018", "09:00", "A"));
        listaHorarios.add(new Agenda("31/12/2018", "15:00", "A"));

        //ArrayAdapter<Agenda> adapterAgenda = new ArrayAdapter<>(this, R.layout.layout_horarios, listaHorarios);
        //listViewHorarios.setAdapter(adapterAgenda);

        myAdapter = new AgendaAdapter(this, R.layout.layout_horarios, listaHorarios);
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
                Toast.makeText(OfertarCursoActivity.this, "Seleção: " + categoria.getDescricao(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(OfertarCursoActivity.this, "Selections cleared.", Toast.LENGTH_SHORT).show();
            }
        });

        // se estiver editando, então preenche a tela
        if (getIntent().hasExtra("ID_OFERTA_EDIT")) {
            toolbar.setTitle("Editar Oferta");


            ofertaEdit = app.ofertaDAO().findByID(bundle.getInt("ID_OFERTA_EDIT"));
            Sistema sistema = app.sistemaDAO().findByID(ofertaEdit.getSistema());
            editTitulo.setText(ofertaEdit.getTitulo());
            editDescricao.setText(ofertaEdit.getDescricao());
            editValorHora.setText(String.valueOf(ofertaEdit.getValor_hora()));
            autoCompleteSistema.setText(sistema.getNome());

        } else {
            toolbar.setTitle("Nova Oferta");
        }
    }

    public void addHorario(View v) {
        addAgendaDatePicker();
        //listaHorarios.add(new Agenda("15/12/2018", "13:00", "A"));
        //myAdapter.notifyDataSetChanged();
    }


    private ArrayList<Sistema> getListaSistemas() {

        AppDatabase app = AppDatabase.getDatabase(this);
        List<Sistema> sistemas = app.sistemaDAO().findAll();
        return new ArrayList<Sistema>(sistemas);
    }

    /**
     * retorna todas as categorias, e já converte para arraylist, para conseguir utilizar o
     * arrayAdapter com spinner
     *
     * @return
     */
    private ArrayList<Categoria> getCategorias() {
        AppDatabase app = AppDatabase.getDatabase(this);
        List<Categoria> categorias = app.categoriaDAO().findAll();
        return new ArrayList<Categoria>(categorias);
    }


    public void salvar(View v) {
        AppDatabase app = AppDatabase.getDatabase(this);
        UsuarioLogado usuarioLogado = UsuarioLogado.getInstance();

        if (!validarCampos()) {
            return;
        }
        // verifica se o sistema informado textualmente já existe de fato
        Sistema sistema = app.sistemaDAO().findByName(autoCompleteSistema.getText().toString());
        if (sistema == null) {
            // se sistema não existir, então insere
            sistema = new Sistema();
            sistema.setNome(autoCompleteSistema.getText().toString());
            long idSistema = app.sistemaDAO().inserir(sistema);
            sistema.setID((int) idSistema);
        }

        Oferta oferta;
        // se estiver editando, entao busca o bendito
        if (getIntent().hasExtra("ID_OFERTA_EDIT")) {
            oferta = app.ofertaDAO().findByID(bundle.getInt("ID_OFERTA_EDIT"));
        } else {
            oferta = new Oferta();
        }


        oferta.setIdcategoria(1);
        oferta.setDescricao(editDescricao.getText().toString());
        oferta.setTitulo(editTitulo.getText().toString());
        oferta.setValor_hora(Float.parseFloat(editValorHora.getText().toString()));
        oferta.setStatus("P");
        oferta.setSistema(sistema.getID());

        oferta.setUsuario(usuarioLogado.getID());


        // se estiber editando
        if (getIntent().hasExtra("ID_OFERTA_EDIT")) {
            long idOferta = app.ofertaDAO().editar(oferta);

            if(idOferta > 0 ){
                // apaga toda a agenda desta oferta, para inserir novamente
                app.agendaDAO().deleteByOfertaID(oferta.getID());
                // varre o array de agendas e coloca o código da oferta
                for (Agenda a : listaHorarios) {
                    a.setOferta(oferta.getID());
                    app.agendaDAO().inserir(a);
                }

                Toast.makeText(this, "Oferta atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OfertarCursoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Problemas ao atualizar oferta!", Toast.LENGTH_SHORT).show();
            }

        } else { // se estiver inserindo
            long idOferta = app.ofertaDAO().inserir(oferta);
            // se salvou a oferta com sucesso, então insere na agenda
            if (idOferta > 0) {
                // varre o array de agendas e coloca o código da oferta
                for (Agenda a : listaHorarios) {
                    a.setOferta((int) idOferta);
                    app.agendaDAO().inserir(a);
                }
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OfertarCursoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Erro ao cadastrar oferta!", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(OfertarCursoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    private void addAgendaDatePicker() {
        // inicializa a data para a data de hoje
        initDateTimeData();
        // cria uma data para ser a data padrao do datepicker
        Calendar cDefault = Calendar.getInstance();
        cDefault.set(year, month, day);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                this,
                cDefault.get(Calendar.YEAR),
                cDefault.get(Calendar.MONTH),
                cDefault.get(Calendar.DAY_OF_MONTH)
        );
        // seta a data mínima como sendo a data de hoje
        datePickerDialog.setMinDate(Calendar.getInstance());
        // seta um cancel listener
        datePickerDialog.setOnCancelListener(this);

        // chama o calendário para seleção do usuário
        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }

    // inicializa as variaveis de data e hora para a data atual
    private void initDateTimeData() {
        if (year == 0) {
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
        }
    }


    @Override
    public void onCancel(DialogInterface dialogInterface) {
        // se usuário cancela a seleção da data, zera as variáveis de controle da data
        year = month = day = hour = minute = 0;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int iYear, int iMonth, int iDay) {

        // cria um calendario para passar ao TimePicker
        Calendar tDefault = Calendar.getInstance();
        tDefault.set(year, month, day, hour, minute);
        // ao escolher a data, chama Timepicker, com a hora atual
        year = iYear;
        month = iMonth;
        day = iDay;
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                this,
                tDefault.get(Calendar.HOUR_OF_DAY),
                tDefault.get(Calendar.MINUTE),
                true
        );

        // seta o cancel listener
        timePickerDialog.setOnCancelListener(this);
        timePickerDialog.show(getFragmentManager(), "timePickerDialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int ihora, int iminuto, int isegundo) {
        hour = ihora;
        minute = iminuto;

        String dataFormatada;
        // verifica se o dia for menor q 10, para que seja inserido um 0 antes. Mesma coisa
        // ocorre para o mês, mas no caso do mês ele inicia com 0=janeiro, então tem q testar sempre o +1
        dataFormatada = (day < 10 ? "0" + String.valueOf(day) : String.valueOf(day)) + "/" +
                (month + 1 < 10 ? "0" + String.valueOf((month + 1)) : String.valueOf(month + 1)) + "/" +
                String.valueOf(year);

        String horaFormatada;
        // verifica se munito for menor q 10, para preencher com zero
        horaFormatada = (ihora < 10 ? "0" + String.valueOf(ihora) : String.valueOf(ihora)) + ":" +
                (iminuto < 10 ? "0" + String.valueOf(iminuto) : String.valueOf(iminuto));

        listaHorarios.add(new Agenda(dataFormatada, horaFormatada, "A"));
        myAdapter.notifyDataSetChanged();
    }

    /**
     * valida campos antes de inserir
     *
     * @return
     */
    public Boolean validarCampos() {
        // valida o campo de título do anúncio
        if (editTitulo.getText().toString().equals("")) {
            Toast.makeText(this, "Título do anúncio é obrigatório", Toast.LENGTH_SHORT).show();
            editTitulo.requestFocus();
            return false;
        }

        // valida o campo de descrição do anúncio
        if (editDescricao.getText().toString().equals("")) {
            Toast.makeText(this, "Descrição do anúncio é obrigatório", Toast.LENGTH_SHORT).show();
            editDescricao.requestFocus();
            return false;
        }
        // valida o campo de valor da hora do anúncio
        if (editValorHora.getText().toString().equals("")) {
            Toast.makeText(this, "Valor da hora é obrigatório", Toast.LENGTH_SHORT).show();
            editValorHora.requestFocus();
            return false;
        }

        // verifica se inserido alguma data de agenda
        if (listaHorarios.size() <= 0) {
            Toast.makeText(this, "É obrigatório informar pelo menos um horário disponível", Toast.LENGTH_SHORT).show();
            return false;
        }
        // valida o campo de valor da hora do anúncio
        if (autoCompleteSistema.getText().toString().equals("")) {
            Toast.makeText(this, "Sistema é obrigatório", Toast.LENGTH_SHORT).show();
            autoCompleteSistema.requestFocus();
            return false;
        }
        return true;
    }
}
