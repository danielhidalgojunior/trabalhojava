package com.example.aluno.dados.Controller;

import android.content.Context;

import com.example.aluno.dados.Model.Produto;
import com.example.aluno.dados.Model.DAO.ProdutoDAO;

import java.util.ArrayList;

public class ProdutoController {
    ProdutoDAO produtoDAO;

    public ProdutoController(Context context) {

        produtoDAO = new ProdutoDAO(context);
    }

    public long inserirDados(Produto produto){

        return produtoDAO.insereDados(produto);
    }

    public long alterarDados(Produto produto){

        return produtoDAO.alterarDados(produto);
    }

    public long apagarDados(Produto produto){

        return produtoDAO.apagarDados(produto);
    }

    public ArrayList<Produto> listarDados(){
        return produtoDAO.listarDados();
    }
}
