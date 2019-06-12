package com.example.aluno.dados.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

import com.example.aluno.dados.Model.Produto;

import java.util.ArrayList;

public class ProdutoDAO extends SQLiteOpenHelper{

    private static  final  String BANCO_DADOS = "BDSistemas";
    private static  final  String TABELA = "Produto";
    private  static  final  int VERSION = 1;

    private  static  final String CAMPO_ID = "_id";
    private  static  final String CAMPO_MARCA = "marca";
    private  static  final String CAMPO_MODELO = "modelo";
    private  static  final String CAMPO_PRECO = "preco";


    public ProdutoDAO( Context context) {
        super(context, BANCO_DADOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +
                " (" + CAMPO_ID +
                " INTEGER PRIMARY KEY NOT NULL, " +
                CAMPO_MARCA + " TEXT, " +
                CAMPO_MODELO + " TEXT, "+
                CAMPO_PRECO + " TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insereDados(Produto produto){
        long resposta = 0;

        try {
            ContentValues valores = new ContentValues();
            valores.put(CAMPO_MARCA, produto.getMarca());
            valores.put(CAMPO_MODELO, produto.getModelo());
            valores.put(CAMPO_PRECO, produto.getPreco());

            resposta = getWritableDatabase().insert(TABELA, null, valores);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public long alterarDados(Produto produto){
        long resposta = 0;

        try {
            String where = CAMPO_ID + " = " + produto.getId();
            ContentValues valores = new ContentValues();
            valores.put(CAMPO_MARCA, produto.getMarca());
            valores.put(CAMPO_MODELO, produto.getModelo());
            valores.put(CAMPO_PRECO, produto.getPreco());

            resposta = getWritableDatabase().update(TABELA,  valores, where, null);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public long apagarDados(Produto produto){
        long resposta = 0;

        try {
            String where = CAMPO_ID + " = " + produto.getId();

            resposta = getWritableDatabase().delete(TABELA,  where, null);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public ArrayList<Produto> listarDados(){
        ArrayList<Produto> lista = null;
        Cursor cursor = getWritableDatabase().query(TABELA, new String[]{CAMPO_ID, CAMPO_MARCA,
                        CAMPO_MODELO, CAMPO_PRECO}, null,
                null, null, null,
                null);
        lista = new ArrayList<Produto>();
        Produto produto;

        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            do {
                produto = new Produto(cursor.getInt(cursor.getColumnIndex(CAMPO_ID)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_MARCA)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_MODELO)),
                        cursor.getDouble(cursor.getColumnIndex(CAMPO_PRECO)));
                lista.add(produto);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista;
    }
}
