package com.example.aluno.dados.View;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.dados.Controller.PessoaController;
import com.example.aluno.dados.Model.Cliente;
import com.example.aluno.dados.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PessoaView extends AppCompatActivity {
    EditText etNome,etTelefone,etEmail, etData;
    PessoaController controler;
    private long id;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        etNome = findViewById(R.id.etnome);
        etTelefone = findViewById(R.id.ettelefone);
        etEmail = findViewById(R.id.etemail);
        etData = findViewById(R.id.etdata);

        controler = new PessoaController(this);

        etData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(PessoaView.this, dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        etData.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClickInserir (View view) {
        long novoID;

        Cliente cliente = new Cliente(0,etNome.getText().toString(),
                etTelefone.getText().toString(),
                etEmail.getText().toString(),
                etData.getText().toString());

        novoID = controler.inserirDados(cliente);

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

        Cliente cliente = new Cliente(id, etNome.getText().toString(),
                etTelefone.getText().toString(),
                etEmail.getText().toString(),
                etData.getText().toString());

        novoID = controler.alterarDados(cliente);

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

        Cliente cliente = new Cliente(id, etNome.getText().toString(),
                etTelefone.getText().toString(),
                etEmail.getText().toString(),
                etData.getText().toString());

        novoID = controler.apagarDados(cliente);

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
        etNome.setText("");
        etTelefone.setText("");
        etEmail.setText("");
        etData.setText("");
    }

    public void onClickListarDados(View view){
        ArrayList<String> listaNomes = new ArrayList<String>();
        final ArrayList<Cliente> lista = controler.listarDados();

        for(int i = 0; i < lista.size(); i++){
            listaNomes.add(lista.get(i).getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNomes);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clientes");
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

    private void mostrarDados(Cliente cliente){
        id = cliente.getId();
        etNome.setText(cliente.getNome());
        etTelefone.setText(cliente.getTelefone());
        etEmail.setText(cliente.getEmail());
        etData.setText(cliente.getDataNascimento());
    }
}




