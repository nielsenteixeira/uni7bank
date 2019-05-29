package com.bank.uni7bank.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@TypeDef(name = "tipo_conta", typeClass = PostgreSQLEnumType.class)
public class Conta extends BaseEntity{
    private int numero;

    @Enumerated(STRING)
    @Column(columnDefinition = "tipo_conta")
    @Type(type = "tipo_conta")
    private TipoConta tipoConta;
    private double saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Conta() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}