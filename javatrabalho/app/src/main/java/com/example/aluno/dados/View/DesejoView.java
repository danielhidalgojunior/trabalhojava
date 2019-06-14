package com.example.aluno.dados.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aluno.dados.Controller.PessoaController;
import com.example.aluno.dados.Controller.ProdutoController;
import com.example.aluno.dados.Model.Cliente;
import com.example.aluno.dados.Model.Produto;
import com.example.aluno.dados.R;

import java.util.ArrayList;
import java.util.List;

public class DesejoView extends AppCompatActivity {

    private Cliente cliente;
    private Produto produto;

    private ProdutoController produtoController;
    private PessoaController clienteController;

    private Spinner spProdutoId, spClienteId;
    private Button btnEmail, btnTelefone;
    private EditText etValorMinimo, etValorMaximo;

    private boolean creatingProduto, creatingCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela4);

        produtoController = new ProdutoController(this);
        clienteController = new PessoaController(this);

        spProdutoId = findViewById(R.id.spProdutoId);
        spClienteId = findViewById(R.id.spClienteId);
        btnEmail = findViewById(R.id.btnEmail);
        btnTelefone = findViewById(R.id.btnTelefone);
        etValorMinimo = findViewById(R.id.etValorMinimo);
        etValorMaximo = findViewById(R.id.etValorMaximo);

        final List<String> listaProduto = new ArrayList<String>();
        final List<String> listaCliente = new ArrayList<String>();

        final ArrayList<Produto> arrayProduto = produtoController.listarDados();

        for(int i = 0; i < arrayProduto.size(); i++){
            listaProduto.add(arrayProduto.get(i).getModelo());
        }

        final ArrayList<Cliente> arrayCliente = clienteController.listarDados();

        for(int i = 0; i < arrayCliente.size(); i++){
            listaCliente.add(arrayCliente.get(i).getNome());
        }

        ArrayAdapter<String> dataAdapterProduto = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, listaProduto);
        ArrayAdapter<String> dataAdapterCliente = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, listaCliente);


        dataAdapterProduto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterCliente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spProdutoId.setAdapter(dataAdapterProduto);
        spClienteId.setAdapter(dataAdapterCliente);

        creatingProduto = true;
        spProdutoId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!creatingProduto){
                    produto = arrayProduto.get(position);
                    VerificarPrecos();
                }
                else{
                    creatingProduto = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        creatingCliente = true;
        spClienteId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!creatingCliente){
                    cliente = arrayCliente.get(position);
                    VerificarPrecos();
                }
                else{
                    creatingCliente = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etValorMinimo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VerificarPrecos();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etValorMaximo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VerificarPrecos();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void VerificarPrecos(){
        if (produto == null || cliente == null || etValorMaximo.getText().length() == 0 || etValorMinimo.getText().length() == 0) {
            btnEmail.setEnabled(false);
            btnTelefone.setEnabled(false);

            return;
        }

        float valorMinimo = Float.valueOf(etValorMinimo.getText().toString());
        float valorMaximo = Float.valueOf(etValorMaximo.getText().toString());
        float precoProduto = produto.getPreco();

        if (precoProduto >= valorMinimo && precoProduto <= valorMaximo){
            btnEmail.setEnabled(true);
            btnTelefone.setEnabled(true);
        }
        else{
            btnEmail.setEnabled(false);
            btnTelefone.setEnabled(false);
        }
    }

    public void onClickEnviaEmail(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , cliente.getEmail());
        i.putExtra(Intent.EXTRA_SUBJECT, "Produto desejado " + produto.getModelo());
        i.putExtra(Intent.EXTRA_TEXT   , "O produto da sua lista de desejos está pelo preço de R$ " + produto.getPreco());
        try {
            startActivity(Intent.createChooser(i, "Enviar email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DesejoView.this, "Nenhum cliente de email está instalado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickLigar(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);

        String f = cliente.getTelefone();

        intent.setData(Uri.parse("tel:" + cliente.getTelefone()));
        startActivity(intent);
    }
}
