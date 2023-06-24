package com.example.appdiaristas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    public Button bt_IrLogin;
    private DatabaseHelper databaseHelper;

    SQLiteDatabase db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        bt_IrLogin = findViewById(R.id.bt_IrLogin);

        bt_IrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TelaLogin.class);
                startActivity(intent);
            }
        });

        criarAbrirDB();
    }

    public void criarAbrirDB(){
        try{
            db=openOrCreateDatabase("bancoAppDiarista", MODE_PRIVATE, null);
        }catch (Exception ex){
            msg("Erro ao abrir ou ao criar Banco de Dados");
        }
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos(id INTEGER PRIMARY KEY, nome TEXT, fone TEXT);");
        }catch (Exception ex){
            msg("Erro ao criar Tabela");
        }finally {
            msg("Tabela criada com sucesso");
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        fechardb();
    }

    public void fechardb() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public void msg(String txt) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage(txt);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}