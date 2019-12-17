package com.example.loginrest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginrest.R;
import com.example.loginrest.entities.Login;
import com.example.loginrest.entities.Token;
import com.example.loginrest.service.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class RegisterActivity extends AppCompatActivity {

    TextView txtEmail;
    TextView txtSenha;
    boolean sucess = false;
    Token token = new Token();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtEmail = (TextView) findViewById(R.id.txtUsuario);
        txtSenha = (TextView) findViewById(R.id.txtSenha);
    }

    public void save(View view) {
        Login login = new Login(txtEmail.getText().toString(), txtSenha.getText().toString());

        RetrofitFactory.getService().register(login).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                token = response.body();
                sucess = true;
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

                sucess = false;
            }
        });

        if(sucess) {
            Toast toast = Toast.makeText(this, R.string.register, Toast.LENGTH_LONG);
            toast.show();
            setIntent(new Intent(this, MainActivity.class));
        }else{
            Toast toast = Toast.makeText(this, "Houve uma falha ou processar seus dados.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void cancel(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}