package com.example.appdiaristas;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdiaristas.DatabaseHelper;

public class esqueceuSenha extends AppCompatActivity {

    private EditText etNovaSenha;
    private Button btnAtualizar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        etNovaSenha = findViewById(R.id.senha2);
        btnAtualizar = findViewById(R.id.atualizar);
        databaseHelper = new DatabaseHelper(this);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarSenha();
            }
        });
    }

    private void atualizarSenha() {
        String novaSenha = etNovaSenha.getText().toString();

        // Recupere o ID do usuário da tela anterior (se necessário)
        int idUsuario = 1; // Substitua pelo ID do usuário

        // Atualize a senha do usuário no banco de dados
        Usuario usuario = databaseHelper.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            int linhasAfetadas = databaseHelper.atualizarUsuario(usuario);
            if (linhasAfetadas > 0) {
                Toast.makeText(this, "Senha atualizada com sucesso", Toast.LENGTH_SHORT).show();
                abrirTelaLogin();
            } else {
                Toast.makeText(this, "Falha ao atualizar senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirTelaLogin() {
        Intent intent = new Intent(esqueceuSenha.this, TelaLogin.class);
        startActivity(intent);
        finish(); // Finalize a atividade atual para que o usuário não possa voltar para ela pressionando o botão Voltar
    }
}
