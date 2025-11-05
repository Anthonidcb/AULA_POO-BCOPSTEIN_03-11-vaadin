package com.anthoni;

public class Pessoa {
    private String nome;
    private int peso;
    private int altura;

    public Pessoa(String nome, int peso, int altura) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getAltura() {
        return altura;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                '}';
    }
}

