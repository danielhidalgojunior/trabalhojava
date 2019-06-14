package com.example.aluno.dados.View;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.dados.Controller.ProdutoController;
import com.example.aluno.dados.Model.Produto;
import com.example.aluno.dados.R;

import java.util.ArrayList;

public class ProdutoView extends AppCompatActivity {
    EditText etMarca, etModelo, etPreco;
    ProdutoController controller;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        etMarca = findViewById(R.id.etMarca);
        etModelo = findViewById(R.id.etModelo);
        etPreco = findViewById(R.id.etPreco);

        controller = new ProdutoController(this);
    }

    public void onClickInserir (View view) {
        long novoID;

        Produto produto = new Produto(0,etMarca.getText().toString(),
                etModelo.getText().toString(),
                Float.valueOf(etPreco.getText().toString()));

        novoID = controller.inserirDados(produto);

        if(novoID != -1){
            limpaTela();
            Toast.makeText(this, "Dados inseridos com sucesso, com ID = " + novoID,
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Erro na inserção dos dados!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickAlterar (View view) {
        long novoID;

        Produto produto = new Produto(id, etMarca.getText().toString(),
                etModelo.getText().toString(),
                Float.valueOf(etPreco.getText().toString()));

        novoID = controller.alterarDados(produto);

        if(novoID != -1){
            limpaTela();
            Toast.makeText(this, "Dados alterados com sucesso, com ID = " + novoID,
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Erro na alteração dos dados!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickApagar (View view) {
        long novoID;

        Produto produto = new Produto(id, etMarca.getText().toString(),
                etModelo.getText().toString(),
                Float.valueOf(etPreco.getText().toString()));

        novoID = controller.apagarDados(produto);

        if(novoID != -1){
            limpaTela();
            Toast.makeText(this, "Dados apagados com sucesso, com ID = " + novoID,
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Erro ao apagar os dados!", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpaTela(){
        etMarca.setText("");
        etModelo.setText("");
        etPreco.setText("");
    }

    public void onClickListarDados(View view){
        ArrayList<String> listaProdutos = new ArrayList<String>();
        final ArrayList<Produto> lista = controller.listarDados();

        for(int i = 0; i < lista.size(); i++){
            listaProdutos.add(lista.get(i).getModelo());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProdutos);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Produtos");
        builder.setCancelable(true);

        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                mostrarDados(lista.get(item));
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void mostrarDados(Produto produto){
        id = produto.getId();
        etMarca.setText(produto.getMarca());
        etModelo.setText(produto.getModelo());
        etPreco.setText(Float.toString(produto.getPreco()));
    }
}
