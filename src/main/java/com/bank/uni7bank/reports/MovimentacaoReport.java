package com.bank.uni7bank.reports;

import java.time.ZonedDateTime;
import java.util.Date;

public class MovimentacaoReport {

    private ZonedDateTime data;
    private String cliente;
    private String agencia;
    private int contaCliente;
    private int contaDestino;
    private double valorMovimentacao;
    private String tipoMovimentacao;
    private double saldo;

    public MovimentacaoReport(ZonedDateTime data, String cliente, String agencia, int contaCliente, int contaDestino, double valorMovimentacao, String tipoMovimentacao, double saldo) {
        this.data = data;
        this.cliente = cliente;
        this.agencia = agencia;
        this.contaCliente = contaCliente;
        this.contaDestino = contaDestino;
        this.valorMovimentacao = valorMovimentacao;
        this.saldo = saldo;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public String getCliente() {
        return cliente;
    }

    public String getAgencia() {
        return agencia;
    }

    public int getContaCliente() {
        return contaCliente;
    }

    public int getContaDestino() {
        return contaDestino;
    }

    public double getValorMovimentacao() {
        return valorMovimentacao;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    @Override
    public String toString() {
        String format = "|%1$-30s|%2$-10s|%3$-10s|%4$-30s|%5$-10s|%6$-10s|%6$-20s|%7$-20s|\n";
        return String.format(format, data.toLocalDateTime(), agencia, contaCliente, cliente, (contaDestino == 0 ? " - ": String.valueOf(contaDestino)), valorMovimentacao, tipoMovimentacao, saldo);
    }
}
