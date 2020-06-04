package com.diegocebo.catlockdesign.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diegocebo.catlockdesign.MainActivity;
import com.diegocebo.catlockdesign.R;
import com.diegocebo.catlockdesign.network.ApiClient;
import com.diegocebo.catlockdesign.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText nombre, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.etUser);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPass);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });

    }

    private void goRegister() {
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setPassword(password.getText().toString());

        if(usuario.getNombre().isEmpty() || usuario.getEmail().isEmpty() || usuario.getPassword().isEmpty()){
            Toast.makeText(this,"Fill all the fields", Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Usuario> call = apiService.usuarioRegister(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario = response.body();
                if(usuario !=null){
                    registerSuccess();
                }else{
                    registerError();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                t.getMessage();
                registerError();
            }
        });
    }

    public void onClick(View v) {

    }

    public void registerSuccess() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void registerError() {
        Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_LONG).show();
    }
}
