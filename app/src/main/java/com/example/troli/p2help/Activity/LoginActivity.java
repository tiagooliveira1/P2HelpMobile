package com.example.troli.p2help.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
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
import java.util.List;

import com.example.troli.p2help.DAO.UsuarioDAO;
import com.example.troli.p2help.MainActivity;
import com.example.troli.p2help.R;
import com.example.troli.p2help.Util.Util;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    private EditText editLogin;
    private EditText editSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editLogin = (EditText) findViewById(R.id.editLoginUsuario);
        editSenha = (EditText) findViewById(R.id.editPassword);
    }

    public void logarUsuario(View v){
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        long idUsuario = usuarioDAO.validaUsuario(login, Util.toMD5(senha));
        if(idUsuario > 0){
            Toast.makeText(this, "Usuário Logado com Sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("ID_USUARIO",idUsuario);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Usuário não Cadastrado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarUsuario(View v){
        Intent intent = new Intent(LoginActivity.this,RegistroUsuarioActivity.class);
        startActivity(intent);
    }

}

