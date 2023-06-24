package com.example.appdiaristas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "meu_banco.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela no banco de dados
        String createTableQuery = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY, nome TEXT, email TEXT, cpf TEXT, idade INTEGER, senha TEXT);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualização do banco de dados (caso necessário)
    }

    public long inserirUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("cpf", usuario.getCpf());
        values.put("idade", usuario.getIdade());
        values.put("senha", usuario.getSenha());

        long id = db.insert("usuarios", null, values);

        db.close();

        return id;
    }

    public Usuario buscarUsuarioPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {"id", "nome", "email", "cpf", "idade", "senha"};
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);

        Usuario usuario = null;

        if (cursor != null && cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndexOrThrow("id");
            int nomeIndex = cursor.getColumnIndexOrThrow("nome");
            int emailIndex = cursor.getColumnIndexOrThrow("email");
            int cpfIndex = cursor.getColumnIndexOrThrow("cpf");
            int idadeIndex = cursor.getColumnIndexOrThrow("idade");
            int senhaIndex = cursor.getColumnIndexOrThrow("senha");

            int userId = cursor.getInt(userIdIndex);
            String nome = cursor.getString(nomeIndex);
            String email = cursor.getString(emailIndex);
            String cpf = cursor.getString(cpfIndex);
            int idade = cursor.getInt(idadeIndex);
            String senha = cursor.getString(senhaIndex);

            usuario = new Usuario(userId, nome, email, cpf, idade, senha);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return usuario;
    }

    public int atualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("cpf", usuario.getCpf());
        values.put("idade", usuario.getIdade());
        values.put("senha", usuario.getSenha());

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(usuario.getId())};

        int rowsAffected = db.update("usuarios", values, whereClause, whereArgs);

        db.close();

        return rowsAffected;
    }

    public int excluirUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};

        int rowsAffected = db.delete("usuarios", whereClause, whereArgs);

        db.close();

        return rowsAffected;
    }

    public boolean autenticarUsuario(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Consulta ao banco de dados para verificar se o email e senha correspondem a um usuário válido
        String query = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, senha});

        boolean autenticado = false;

        if (cursor != null && cursor.getCount() > 0) {
            autenticado = true;
        }

        if (cursor != null) {
            cursor.close();
        }

        return autenticado;
    }
}
