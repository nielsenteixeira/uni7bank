package com.bank.uni7bank.Repository;

import com.bank.uni7bank.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByContaOrigemId(long contaOrigemId);
    List<Movimentacao> findByContaOrigemAgenciaId(long agenciaId);
    List<Movimentacao> findByModificadoEmBetween(Date dataInicio, Date dataFim);
}
