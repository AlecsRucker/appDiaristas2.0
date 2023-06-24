package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPrincipal extends AppCompatActivity {

    public Button LimpezaCasa;
    public Button LimpezaEscritorio;
    public Button LimpezaComercial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        LimpezaCasa = findViewById(R.id.LimpezaCasa);
        LimpezaEscritorio = findViewById(R.id.LimpezaEscritorio);
        LimpezaComercial = findViewById(R.id.LimpezaComercial);

        LimpezaComercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPrincipal.this,DetalhesFaxineira.class);
                startActivity(intent);
            }
        });

        LimpezaCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPrincipal.this,DetalhesFaxineira.class);
                startActivity(intent);
            }
        });

        LimpezaEscritorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPrincipal.this,DetalhesFaxineira.class);
                startActivity(intent);
            }
        });
    }
}