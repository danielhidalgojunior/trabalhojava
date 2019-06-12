package com.example.aluno.dados.Model;

public class Produto {
    private long id;
    private String marca;
    private String modelo;
    private double preco;

    public Produto(long id, String marca, String modelo, double preco) {
        this.setId(id);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setPreco(preco);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
