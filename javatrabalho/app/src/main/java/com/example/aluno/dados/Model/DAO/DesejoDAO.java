package com.example.aluno.dados.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

import com.example.aluno.dados.Model.Desejo;

import java.util.ArrayList;

public class DesejoDAO extends SQLiteOpenHelper{

    private static  final  String BANCO_DADOS = "BDSistemas";
    private static  final  String TABELA = "Desejo";
    private  static  final  int VERSION = 1;

    private  static  final String CAMPO_ID = "_id";
    private  static  final String CAMPO_ID_CLIENTE = "idcliente";
    private  static  final String CAMPO_ID_PRODUTO = "idproduto";
    private  static  final String CAMPO_VALORMINIMO = "valorminimo";
    private  static  final String CAMPO_VALORMAXIMO = "valormaximo";


    public DesejoDAO( Context context) {
        super(context, BANCO_DADOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +
                " (" + CAMPO_ID +
                " INTEGER PRIMARY KEY NOT NULL, " +
                CAMPO_ID_CLIENTE + " TEXT, " +
                CAMPO_ID_PRODUTO + " TEXT, "+
                CAMPO_VALORMINIMO + " TEXT, "+
                CAMPO_VALORMAXIMO + " TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insereDados(Desejo desejo){
        long resposta = 0;

        try {
            ContentValues valores = new ContentValues();
            valores.put(CAMPO_ID_CLIENTE, desejo.getIdcliente());
            valores.put(CAMPO_ID_PRODUTO, desejo.getIdproduto());
            valores.put(CAMPO_VALORMINIMO, desejo.getValorminimo());
            valores.put(CAMPO_VALORMAXIMO, desejo.getValormaximo());

            resposta = getWritableDatabase().insert(TABELA, null, valores);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public long alterarDados(Desejo desejo){
        long resposta = 0;

        try {
            String where = CAMPO_ID + " = " + desejo.getId();
            ContentValues valores = new ContentValues();
            valores.put(CAMPO_ID_CLIENTE, desejo.getIdcliente());
            valores.put(CAMPO_ID_PRODUTO, desejo.getIdproduto());
            valores.put(CAMPO_VALORMINIMO, desejo.getValorminimo());
            valores.put(CAMPO_VALORMAXIMO, desejo.getValormaximo());

            resposta = getWritableDatabase().update(TABELA,  valores, where, null);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public long apagarDados(Desejo desejo){
        long resposta = 0;

        try {
            String where = CAMPO_ID + " = " + desejo.getId();

            resposta = getWritableDatabase().delete(TABELA,  where, null);
        }
        catch(Exception ex){
            resposta = -1;
        }

        return resposta;
    }

    public ArrayList<Desejo> listarDados(){
        ArrayList<Desejo> lista = null;
        Cursor cursor = getWritableDatabase().query(TABELA, new String[]{CAMPO_ID, CAMPO_ID_CLIENTE,
                        CAMPO_ID_PRODUTO, CAMPO_VALORMINIMO, CAMPO_VALORMAXIMO}, null,
                null, null, null,
                null);
        lista = new ArrayList<Desejo>();
        Desejo desejo;

        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            do {
                desejo = new Desejo(cursor.getInt(cursor.getColumnIndex(CAMPO_ID)),
                        cursor.getInt(cursor.getColumnIndex(CAMPO_ID_CLIENTE)),
                        cursor.getInt(cursor.getColumnIndex(CAMPO_ID_PRODUTO)),
                        cursor.getDouble(cursor.getColumnIndex(CAMPO_VALORMINIMO)),
                        cursor.getDouble(cursor.getColumnIndex(CAMPO_VALORMAXIMO)));
                lista.add(desejo);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista;
    }
}
