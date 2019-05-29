package com.bank.uni7bank.model;

import javax.persistence.Entity;

@Entity
public class Agencia extends BaseEntity {
    private int numero;
    private String nome;
    private String endereco;

    public Agencia() {
    }

    public Agencia(int numero, String nome, String endereco) {
        this.numero = numero;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
