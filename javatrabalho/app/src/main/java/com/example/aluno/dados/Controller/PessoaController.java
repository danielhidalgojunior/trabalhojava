package com.example.aluno.dados.Controller;

import android.content.Context;

import com.example.aluno.dados.Model.DAO.ClienteDAO;
import com.example.aluno.dados.Model.Cliente;

import java.util.ArrayList;

public class PessoaController {
    ClienteDAO clienteDAO;

    public PessoaController(Context context) {

        clienteDAO = new ClienteDAO(context);
    }

    public long inserirDados(Cliente cliente){

        return clienteDAO.insereDados(cliente);
    }

    public long alterarDados(Cliente cliente){

        return clienteDAO.alterarDados(cliente);
    }

    public long apagarDados(Cliente cliente){

        return clienteDAO.apagarDados(cliente);
    }

    public ArrayList<Cliente> listarDados(){
        return clienteDAO.listarDados();
    }
}
