package com.diegocebo.catlockdesign.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button btnLogin, btnGoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.txtEmail);
        etPassword = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoRegister = findViewById(R.id.btnGoRegister);

        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegisterPage();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                Usuario user = new Usuario();


                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {
                    etPassword.setError("Rellena los campos");
                }else{
                    user.setEmail(email);
                    user.setPassword(password);

                    Call<Usuario> call = apiService.login(user);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            Usuario user = response.body();
                            if(user == null){
                                loginError();
                            }else{
                                loginSuccess();
                            }

                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            loginError();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    public void loginValidations() {
        Toast.makeText(getApplicationContext(), "Enter Email and Password", Toast.LENGTH_LONG).show();
    }

    public void loginSuccess() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void loginError() {
        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
    }

    public void goRegisterPage(){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

}
