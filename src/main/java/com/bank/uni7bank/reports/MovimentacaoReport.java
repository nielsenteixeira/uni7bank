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
    private double saldo;

    public MovimentacaoReport(ZonedDateTime data, String cliente, String agencia, int contaCliente, int contaDestino, double valorMovimentacao, double saldo) {
        this.data = data;
        this.cliente = cliente;
        this.agencia = agencia;
        this.contaCliente = contaCliente;
        this.contaDestino = contaDestino;
        this.valorMovimentacao = valorMovimentacao;
        this.saldo = saldo;
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

    @Override
    public String toString() {
        return " MovimentacaoReport{" +
                "data=" + data +
                ", agencia='" + agencia + '\'' +
                ", contaCliente='" + contaCliente + '\'' +
                ", cliente='" + cliente + '\'' +
                ", contaDestino='" + (contaDestino == 0 ? " - ": String.valueOf(contaDestino)) + '\'' +
                ", valorMovimentacao=" + valorMovimentacao +
                ", saldo=" + saldo +
                "} \n";
    }
}
