package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PedidoFinal extends AppCompatActivity {

    public Button pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_final);

        pagar = findViewById(R.id.pagar);

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PedidoFinal.this,PedidoFeito.class);
                startActivity(intent);
            }
        });
    }
}