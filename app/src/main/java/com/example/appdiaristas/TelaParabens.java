package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaParabens extends AppCompatActivity {

    public Button FazerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_parabens);

        FazerLogin = findViewById(R.id.FazerLogin);

        FazerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaParabens.this,TelaLogin.class);
                startActivity(intent);
            }
        });
    }
}