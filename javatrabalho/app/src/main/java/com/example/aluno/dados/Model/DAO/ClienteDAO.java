package com.example.aluno.dados.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

import com.example.aluno.dados.Model.Cliente;

import java.util.ArrayList;

public class ClienteDAO extends SQLiteOpenHelper{

    private static  final  String BANCO_DADOS = "BDSistemas3";
    private static  final  String TABELA = "Cliente";
    private  static  final  int VERSION = 1;

    private  static  final String CAMPO_ID = "_id";
    private  static  final String CAMPO_NOME = "nome";
    private  static  final String CAMPO_TELEFONE = "telefone";
    private  static  final String CAMPO_EMAIL= "email";
    private  static  final String CAMPO_DATANASCIMENTO = "datanascimento";


    public ClienteDAO(Context context) {
        super(context, BANCO_DADOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +
                " (" + CAMPO_ID +
                " INTEGER PRIMARY KEY NOT NULL, " +
                CAMPO_NOME + " TEXT, " +
                CAMPO_TELEFONE + " TEXT, "+
                CAMPO_EMAIL + " TEXT, " +
                CAMPO_DATANASCIMENTO + " TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insereDados(Cliente cliente){
        long resposta = 0;

        try {
            ContentValues valores = new ContentValues();
            valores.put(CAMPO_NOME, cliente.getNome());
            valores.put(CAMPO_TELEFONE, cliente.getTelefone());
            valores.put(CAMPO_EMAIL, cliente.getEmail());
            valores.put(CAMPO_DATANASCIMENTO, cliente.getDataNascimento());

            resposta = getWritableDatabase().insert(TABELA, null, valores);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public long alterarDados(Cliente cliente){
        long resposta = 0;

        try {
            String where = CAMPO_ID + " = " + cliente.getId();
            ContentValues valores = new ContentValues();
            valores.put(CAMPO_NOME, cliente.getNome());
            valores.put(CAMPO_TELEFONE, cliente.getTelefone());
            valores.put(CAMPO_EMAIL, cliente.getEmail());
            valores.put(CAMPO_DATANASCIMENTO, cliente.getDataNascimento());

            resposta = getWritableDatabase().update(TABELA,  valores, where, null);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public long apagarDados(Cliente cliente){
        long resposta = 0;

        try {
            String where = CAMPO_ID + " = " + cliente.getId();

            resposta = getWritableDatabase().delete(TABELA,  where, null);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public ArrayList<Cliente> listarDados(){
        ArrayList<Cliente> lista = null;
        Cursor cursor = getWritableDatabase().query(TABELA, new String[]{CAMPO_ID, CAMPO_NOME,
                        CAMPO_TELEFONE, CAMPO_EMAIL, CAMPO_DATANASCIMENTO}, null,
                null, null, null,
                null);
        lista = new ArrayList<Cliente>();
        Cliente cliente;

        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            do {
                cliente = new Cliente(cursor.getInt(cursor.getColumnIndex(CAMPO_ID)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_NOME)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_TELEFONE)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_DATANASCIMENTO)));
                lista.add(cliente);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista;
    }
}
