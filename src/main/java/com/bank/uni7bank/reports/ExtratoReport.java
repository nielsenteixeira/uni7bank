package com.bank.uni7bank.reports;

import com.bank.uni7bank.model.Movimentacao;

import java.util.ArrayList;
import java.util.List;

public class ExtratoReport {
    private List<MovimentacaoReport> movimentacoesPojo;

    public ExtratoReport(List<Movimentacao> movimentacoes) {
        movimentacoesPojo = new ArrayList<>();

        movimentacoes.forEach((m) -> {
            movimentacoesPojo.add(new MovimentacaoReport(m.getModifiedAt(), m.getContaOrigem().getCliente().getNome(),
                    m.getContaOrigem().getAgencia().getNome(), m.getContaOrigem().getNumero(),
                    m.getContaDestino() != null ? m.getContaDestino().getNumero() : 0, m.getValor(), m.getSaldo()));
        });
    }

    public List<MovimentacaoReport> getMovimentacoesPojo() {
        return movimentacoesPojo;
    }

    @Override
    public String toString() {
        var str = "**** EXTRTATO *** \n";

       for(MovimentacaoReport mov: movimentacoesPojo) {
           str += mov.toString();
       }

       str += " ******************************************* ";
       return str;

    }
}
