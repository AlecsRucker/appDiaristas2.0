package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetalhesFaxineira extends AppCompatActivity {

    public Button Confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_faxineira);

        Confirmar = findViewById(R.id.Confirmar);

        Confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetalhesFaxineira.this,DetalhesLimpeza.class);
                startActivity(intent);
            }
        });
    }
}