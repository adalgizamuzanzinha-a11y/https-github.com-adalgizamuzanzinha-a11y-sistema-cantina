package models;

import java.util.HashMap;
import java.util.Map;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private String categoria;
    private Map<String, Integer> variedades;

    public Produto(int id, String nome, double preco, int quantidade, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.variedades = new HashMap<>();
    }

    public void adicionarVariedade(String tipo, int quantidade) {
        variedades.put(tipo, quantidade);
    }

    // Getters
    public Map<String, Integer> getVariedades() {
        return variedades;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    // Setters
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("%s - %,.0f KZ", nome, preco);
    }
}
