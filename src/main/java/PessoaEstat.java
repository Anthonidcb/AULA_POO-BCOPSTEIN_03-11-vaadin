package com.anthoni;

public class PessoaEstat extends Pessoa{
    private String classPeso;
    private String classAltura;
    
    public PessoaEstat(String nome, int peso, int altura, String classPeso, String classAltura) {
        super(nome, peso, altura);
        this.classPeso = classPeso;
        this.classAltura = classAltura;
    }

    public String getClassPeso() {
        return classPeso;
    }

    public String getClassAltura() {
        return classAltura;
    }
}