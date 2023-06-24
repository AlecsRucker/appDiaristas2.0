package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PedidoFeito extends AppCompatActivity {

    public Button MenuVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_feito);

        MenuVoltar = findViewById(R.id.MenuVoltar);

        MenuVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PedidoFeito.this,TelaPrincipal.class);
                startActivity(intent);
            }
        });
    }
}