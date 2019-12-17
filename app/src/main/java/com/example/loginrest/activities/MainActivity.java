package com.example.loginrest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.loginrest.R;
import com.example.loginrest.entities.Login;
import com.example.loginrest.entities.Token;
import com.example.loginrest.service.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView txtEmail;
    TextView txtSenha;
    boolean sucess;
    Token token = new Token();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = (TextView) findViewById(R.id.txtUsuario);
        txtSenha = (TextView) findViewById(R.id.txtSenha);
    }

    public void entrar(View view) {
        Login login = new Login(txtEmail.getText().toString(), txtSenha.getText().toString());

        RetrofitFactory.getService().register(login).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()) {
                    token = response.body();
                    sucess = true;
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d("ErroLogin", "onFailure: " + t.getMessage());
                sucess = false;
            }
        });

        if(sucess){
            startActivity(new Intent(this, AreaLogadaActivity.class));
        }
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
