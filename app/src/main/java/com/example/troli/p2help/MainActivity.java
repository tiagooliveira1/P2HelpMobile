package com.example.troli.p2help;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.troli.p2help.Activity.CadastrarSistemaActivity;
import com.example.troli.p2help.Activity.ListagemActivity;
import com.example.troli.p2help.Activity.OfertarCursoActivity;
//import com.example.troli.p2help.DAO.ConfigGeralDAO;
//import com.example.troli.p2help.Util.BancoUtil;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Categoria;
import com.facebook.stetho.Stetho;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Carregando informações do serviço P2Help");
        dialog.setMessage("Aguarde o fim da comunicação ...");
        dialog.show();
        getCategoriasAPI();

    }

    public void cadastrarSistema(View v){
        Intent intent = new Intent(MainActivity.this,CadastrarSistemaActivity.class);
        startActivity(intent);
    }

    public void acessaListagemSistemas(View v){
        Intent intent = new Intent(MainActivity.this,ListagemActivity.class);
        startActivity(intent);
    }

    public void acessarOfertarCurso(View v) {
        Intent intent = new Intent(MainActivity.this,OfertarCursoActivity.class);
        startActivity(intent);
    }

    public void getCategoriasAPI() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.25.12:57598/api/Categorias";

        // Request a string response from the provided URL.
        JsonArrayRequest jsonObjectRequest  = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject obj;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                obj = response.getJSONObject(i);
                                checkCategorias(obj);
                                exibirMensagem("Processando categoria " + obj.getString("descricao"));
                            } catch (JSONException e) {

                            }
                        }
                        dialog.dismiss();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        //exibirMensagem("Erro" + error.toString());
                        dialog.setMessage("Erro: Serviço não disponível. \n\n" + error.toString());
                        dialog.show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);
                SharedPreferences settings = getSharedPreferences("P2_Prefs", 0);
                headers.put("Authorization", "Bearer " + settings.getString("tokenSessao", null));
                return headers;
            }
        };
        // Add the request to the RequestQueue.

        queue.add(jsonObjectRequest);
    }

    private void checkCategorias(JSONObject jsonObject)
    {
        AppDatabase app = AppDatabase.getDatabase(MainActivity.this);

        Categoria categoria; // = new Categoria();

        try {
            /* verifica se categoria já foi cadastrada anteriormente */
            categoria = app.categoriaDAO().findByID(jsonObject.getInt("id"));
            if(categoria == null) {
                categoria.setID(jsonObject.getInt("id"));
                categoria.setDescricao(jsonObject.getString("descricao"));
                categoria.setCategoria_mae(jsonObject.getInt("categoriaMae"));
                long resultado = app.categoriaDAO().inserir(categoria);
            } else {
                /* se a descricao do item for diferente da armazenada, entao atualiza */
                if(categoria.getDescricao().equals(jsonObject.getString("descricao"))) {
                    categoria.setDescricao(jsonObject.getString("descricao"));
                    categoria.setCategoria_mae(jsonObject.getInt("categoriaMae"));
                    long resultado = app.categoriaDAO().editar(categoria);
                }
            }


        } catch (JSONException e) {

        }


    }
    private void exibirMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
