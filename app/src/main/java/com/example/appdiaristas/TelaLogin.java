package com.example.appdiaristas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appdiaristas.DatabaseHelper;


public class TelaLogin extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btnEntrar;
    private Button btnCadastro;
    private Button btnEsqueceuSenha;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        etEmail = findViewById(R.id.email);
        etSenha = findViewById(R.id.senha);
        btnEntrar = findViewById(R.id.entrar);
        btnCadastro = findViewById(R.id.bt_cadastro);
        btnEsqueceuSenha = findViewById(R.id.senhaEsqueceu);
        databaseHelper = new DatabaseHelper(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarLogin();
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaCadastro();
            }
        });

        btnEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaEsqueceuSenha();
            }
        });
    }

    private void realizarLogin() {
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        // Realizar a autenticação do usuário no banco de dados
        if (databaseHelper.autenticarUsuario(email, senha)) {
            // Login bem-sucedido, redirecionar para a próxima tela
            exibirMensagem("Login realizado com sucesso");
            abrirTelaPrincipal();
        } else {
            // Credenciais inválidas, exibir mensagem de erro
            exibirMensagem("Credenciais inválidas");
        }
    }

    private void abrirTelaCadastro() {
        Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
        startActivity(intent);
    }

    private void abrirTelaEsqueceuSenha() {
        Intent intent = new Intent(TelaLogin.this, esqueceuSenha.class);
        startActivity(intent);
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(TelaLogin.this, TelaPrincipal.class);
        startActivity(intent);
    }

    private void exibirMensagem(String mensagem) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage(mensagem);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}
