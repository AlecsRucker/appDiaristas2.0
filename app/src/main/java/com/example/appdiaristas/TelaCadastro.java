package com.example.appdiaristas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TelaCadastro extends AppCompatActivity {

    public Button bt_cadastrar;
    public EditText etNome;
    public EditText etEmail;
    public EditText etCPF;
    public EditText etIdade;
    public EditText etSenha;
    public EditText etConfirmarSenha;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        etNome = findViewById(R.id.Nome);
        etEmail = findViewById(R.id.email);
        etCPF = findViewById(R.id.CPF);
        etIdade = findViewById(R.id.idade);
        etSenha = findViewById(R.id.senha);
        etConfirmarSenha = findViewById(R.id.confirmarSenha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
        databaseHelper = new DatabaseHelper(this);

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        // Obtenha os dados dos campos de entrada
        String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();
        String cpf = etCPF.getText().toString();
        int idade = Integer.parseInt(etIdade.getText().toString());
        String senha = etSenha.getText().toString();
        String confirmarSenha = etConfirmarSenha.getText().toString();

        // Verifique se a senha e a confirmação de senha correspondem
        if (!senha.equals(confirmarSenha)) {
            exibirMensagem("As senhas não correspondem");
            return;
        }

        // Crie um objeto de usuário com os dados fornecidos
        Usuario usuario = new Usuario(0, nome, email, cpf, idade, senha);

        // Salve o usuário no banco de dados e obtenha o ID retornado
        long id = databaseHelper.inserirUsuario(usuario);

        if (id != -1) {
            exibirMensagem("Usuário cadastrado com sucesso");
            limparCampos();
            iniciarTelaParabens();
        } else {
            exibirMensagem("Erro ao cadastrar o usuário");
        }
    }

    private void exibirMensagem(String mensagem) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage(mensagem);
        adb.setNeutralButton("OK", null);
        adb.show();
    }

    private void limparCampos() {
        etNome.setText("");
        etEmail.setText("");
        etCPF.setText("");
        etIdade.setText("");
        etSenha.setText("");
        etConfirmarSenha.setText("");
    }

    private void iniciarTelaParabens() {
        Intent intent = new Intent(TelaCadastro.this, TelaParabens.class);
        startActivity(intent);
    }
}
