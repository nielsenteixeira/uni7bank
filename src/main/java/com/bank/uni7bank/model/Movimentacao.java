package com.bank.uni7bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@TypeDef(name = "tipo_movimentacao", typeClass = PostgreSQLEnumType.class)
public class Movimentacao extends BaseEntity{

    @Enumerated(STRING)
    @Column(columnDefinition = "tipo_movimentacao")
    @Type(type = "tipo_movimentacao")
    private TipoMovimentacao tipoMovimentacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_origem_id")
    @JsonIgnore
    private Conta contaOrigem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_destino_id")
    @JsonIgnore
    private Conta contaDestino;

    private double valor;
    private double saldo;

    public Movimentacao() {
    }

    public Movimentacao(TipoMovimentacao tipoMovimentacao, Conta contaOrigem, Conta contaDestino, double valor, double saldo) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.saldo = saldo;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
