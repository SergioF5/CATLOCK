package com.diegocebo.catlockdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBloqueo, btnPreguntas;

        btnBloqueo = findViewById(R.id.btnBlock);
        btnPreguntas = findViewById(R.id.btnPreguntas);

        btnBloqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBlock();
            }
        });
        btnPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPreguntas();
            }
        });
    }

    public void goBlock() {
        Intent i = new Intent(this, SeleccionblockActivity.class);
        startActivity(i);
    }

    public void goPreguntas() {
        Intent i = new Intent(this, SeleccionpregActivity.class);
        startActivity(i);
    }


}
