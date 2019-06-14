package com.example.aluno.dados.Model;

public class Produto {
    private long id;
    private String marca;
    private String modelo;
    private float preco;

    public Produto(long id, String marca, String modelo, float preco) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
