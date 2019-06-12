package com.example.aluno.dados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aluno.dados.View.PessoaView;
import com.example.aluno.dados.View.ProdutoView;
import com.example.aluno.dados.View.DesejoView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void irParaTela2 (View view){

        Intent intent2 = new Intent(getApplicationContext(), PessoaView.class);
        startActivity (intent2);

    }

    public void irParaTela3 (View view){

        Intent intent3 = new Intent(getApplicationContext(), ProdutoView.class);
        startActivity (intent3);

    }


    public void irParaTela4 (View view){

        Intent intent4 = new Intent(getApplicationContext(), DesejoView.class);
        startActivity (intent4);

    }


}
