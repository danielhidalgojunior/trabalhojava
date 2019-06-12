package com.example.aluno.dados.Model;

public class Desejo {
    private long id;
    private long idcliente;
    private long idproduto;
    private double valorminimo;
    private double valormaximo;

    public Desejo(long id, long idcliente, long idproduto, double valorminimo, double valormaximo) {
        this.setId(id);
        this.setIdcliente(idcliente);
        this.setIdproduto(idproduto);
        this.setValorminimo(valorminimo);
        this.setValormaximo(valormaximo);
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(long idproduto) {
        this.idproduto = idproduto;
    }

    public double getValorminimo() {
        return valorminimo;
    }

    public void setValorminimo(double valorminimo) {
        this.valorminimo = valorminimo;
    }

    public double getValormaximo() {
        return valormaximo;
    }

    public void setValormaximo(double valormaximo) {
        this.valormaximo = valormaximo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
