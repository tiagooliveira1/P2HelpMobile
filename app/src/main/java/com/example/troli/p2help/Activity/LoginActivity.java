package com.example.troli.p2help.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.troli.p2help.DAO.AppDatabase;
import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.DAO.Usuario;
import com.example.troli.p2help.DAO.UsuarioDAO;
import com.example.troli.p2help.DAO.UsuarioLogado;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;
import com.example.troli.p2help.Util.Constantes;
import com.example.troli.p2help.Util.Util;
import com.facebook.stetho.Stetho;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.troli.p2help.Util.MySingleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private EditText editLogin;
    private EditText editSenha;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editLogin = (EditText) findViewById(R.id.editLoginUsuario);
        editSenha = (EditText) findViewById(R.id.editLoginSenha);

        mAuth = FirebaseAuth.getInstance();

        Stetho.initializeWithDefaults(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Toast.makeText(this, extras.getString("mensagem_alerta").toString(), Toast.LENGTH_SHORT).show();
            editLogin.setText(extras.getString("email_cadastrado").toString());
        }

    }

    public void logarUsuario(View v) {
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        mAuth.signInWithEmailAndPassword(login, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent mainActivity = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(mainActivity);
                } else {
                    Log.e("", "onComplete: Failed=" + task.getException().getMessage());
                    Toast.makeText(LoginActivity.this, "Erro ao logar."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*AppDatabase app = AppDatabase.getDatabase(this);
        Usuario usuario;
        usuario = app.usuarioDAO().findByLoginAndPassword(login.trim(), senha);

        if (usuario == null) {
            exibirMensagem("Usuário ou senha inválidos.");
        } else {
            UsuarioLogado usuarioLogado = UsuarioLogado.getInstance();
            usuarioLogado.setID(usuario.getID());
            usuarioLogado.setNome(usuario.getNome());

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            if (Constantes.COMUNICA_API == true) {
                getTokenAuth();
            }

            startActivity(intent);
        }*/

    }


    /**
     * Pega um token válido da API e armazena no objeto
     */
    public void getTokenAuth() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constantes.API_ADDRESS+"/api/Token";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("Login", "1");
            jsonBody.put("Pass", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Request a string response from the provided URL.

        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //exibirMensagem(response.getString("token"));
                            // grava o token em Shared Preferences
                            SharedPreferences settings = getSharedPreferences("P2_Prefs", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("tokenSessao", response.getString("token"));
                            editor.commit();

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        exibirMensagem("Erro" + error.toString());
                    }
                }) {
        };
        // Add the request to the RequestQueue.

        queue.add(jsonObjectRequest);

    }


    public void abrirNovoUsuario(View v) {
        Intent intent = new Intent(LoginActivity.this, NovoUsuarioActivity.class);
        startActivity(intent);
    }

    public void registrarUsuario(View v) {
        Intent intent = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);
        startActivity(intent);
    }


    private void exibirMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

}

