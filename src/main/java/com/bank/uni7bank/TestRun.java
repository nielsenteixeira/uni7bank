package com.bank.uni7bank;

import com.bank.uni7bank.Repository.AgenciaRepository;
import com.bank.uni7bank.Repository.ClienteRepository;
import com.bank.uni7bank.Repository.ContaRepository;
import com.bank.uni7bank.Repository.MovimentacaoRepository;
import com.bank.uni7bank.model.*;
import com.bank.uni7bank.reports.ExtratoReport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class TestRun {
    private final AgenciaRepository agenciaRepository;
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;
    private final MovimentacaoRepository movimentacaoRepository;


    public TestRun(AgenciaRepository agenciaRepository, ClienteRepository clienteRepository,
                   ContaRepository contaRepository, MovimentacaoRepository movimentacaoRepository) {
        this.agenciaRepository = agenciaRepository;
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Bean
    @Transactional
    public CommandLineRunner efetuaMovimentacao() {
        return (args) -> {

            var valorTransferencia = 450;
            efetuaTransferenciaParaUni7(valorTransferencia);

            var valorDeposito = 25D;
            efetuaDeposito(valorDeposito);

            var valorSaque = 100D;
            efetuaSaque(valorSaque);

            var valorSaque2 = 50D;
            efetuaSaque(valorSaque2);

            var valorDeposito2 = 135D;
            efetuaDeposito(valorDeposito2);

            var valorTransferencia2 = 200;
            efetuaTransferenciaParaUni7(valorTransferencia2);

//            criaNovaAgenciaContaECliente();
//            var valorTransferencia3 = 10;
//            efetuaTransferenciaParaNovaConta(valorTransferencia3);

            var clienteNielsen = clienteRepository.findByCpfCnpj("02812648309");

            imprimeExtrato(clienteNielsen.get());

            System.out.println("Saldo: R$" + clienteNielsen.get().getContas().get(0).getSaldo());

        };
    }

    @Transactional
    private void criaNovaAgenciaContaECliente() {
        var novaAgencia = new Agencia(2, "Nubank", "Rua abc");
        agenciaRepository.save(novaAgencia);

        var novoCliente = new Cliente("Mateus", "55555555555", TipoPessoa.FISICA);
        clienteRepository.save(novoCliente);

        var novaConta = new Conta(5, TipoConta.POUPANCA, 500, novaAgencia, novoCliente);
        contaRepository.save(novaConta);
    }

    @Transactional
    private void imprimeExtrato(Cliente clienteNielsen) {
        var contaNielsen = contaRepository.findByClienteId(clienteNielsen.getId());

        if (contaNielsen.isPresent()) {
//            var movimentacoes = movimentacaoRepository.findByContaOrigemAgenciaId(contaNielsen.get().getAgencia().getId());
//            var dataInicio = Date.from(LocalDateTime.now().minusDays(15).atZone(ZoneId.systemDefault()).toInstant());
//            var movimentacoes = movimentacaoRepository.findByModificadoEmBetween(dataInicio, new Date() );

            var movimentacoes = movimentacaoRepository.findByContaOrigemId(contaNielsen.get().getId());
            var extrato = new ExtratoReport(movimentacoes);
            System.out.println(extrato.toString());
        }

    }

    @Transactional
    private void efetuaDeposito(double valorDeposito) {
        var clienteNielsen = clienteRepository.findByCpfCnpj("02812648309");

        if(clienteNielsen.isPresent()) {
            var contaNielsen = contaRepository.findByClienteId(clienteNielsen.get().getId());

            if(contaNielsen.isPresent()) {

                contaNielsen.get().setSaldo(contaNielsen.get().getSaldo() + valorDeposito);
                contaRepository.save(contaNielsen.get());

                var deposito = new Movimentacao(TipoMovimentacao.DEPOSITO, contaNielsen.get(), null, valorDeposito, contaNielsen.get().getSaldo());
                movimentacaoRepository.save(deposito);
            }

        }
    }

    @Transactional
    private void efetuaSaque(double valorSaque) {
        var clienteNielsen = clienteRepository.findByCpfCnpj("02812648309");

        if(clienteNielsen.isPresent()) {
            var contaNielsen = contaRepository.findByClienteId(clienteNielsen.get().getId());

            if(contaNielsen.isPresent()) {

                contaNielsen.get().setSaldo(contaNielsen.get().getSaldo() - valorSaque);
                contaRepository.save(contaNielsen.get());

                var deposito = new Movimentacao(TipoMovimentacao.SAQUE, contaNielsen.get(), null, valorSaque, contaNielsen.get().getSaldo());
                movimentacaoRepository.save(deposito);
            }

        }
    }

    @Transactional
    private void efetuaTransferenciaParaUni7(double valorTransferencia) {
        var clienteNielsen = clienteRepository.findByCpfCnpj("02812648309");
        var clienteUni7 = clienteRepository.findByCpfCnpj("12345678000122");

        if(clienteNielsen.isPresent() && clienteUni7.isPresent()) {

            var contaNielsen = contaRepository.findByClienteId(clienteNielsen.get().getId());
            var contaUni7 = contaRepository.findByClienteId(clienteUni7.get().getId());

            if(contaNielsen.isPresent() && contaUni7.isPresent()) {

                if(contaNielsen.get().getSaldo() >= valorTransferencia) {

                    contaNielsen.get().setSaldo(contaNielsen.get().getSaldo() - valorTransferencia);
                    contaUni7.get().setSaldo(contaUni7.get().getSaldo() + valorTransferencia);

                    contaRepository.save(contaNielsen.get());
                    contaRepository.save(contaUni7.get());

                    var transferencia = new Movimentacao(TipoMovimentacao.TRANSFERENCIA, contaNielsen.get(), contaUni7.get(), valorTransferencia, contaNielsen.get().getSaldo());
                    movimentacaoRepository.save(transferencia);
                } else {
                    throw new SaldoInsuficienteException("saldo insuficiente na conta id:" + contaNielsen.get().getId() + ". Saldo: " + contaNielsen.get().getSaldo());
                }
            }
        }
    }

    @Transactional
    private void efetuaTransferenciaParaNovaConta(double valorTransferencia) {
        var clienteNielsen = clienteRepository.findByCpfCnpj("02812648309");
        var novoCliente = clienteRepository.findByCpfCnpj("55555555555");

        if(clienteNielsen.isPresent() && novoCliente.isPresent()) {

            var contaNielsen = contaRepository.findByClienteId(clienteNielsen.get().getId());
            var novaContaCriada = contaRepository.findByClienteId(novoCliente.get().getId());

            if(contaNielsen.isPresent() && novaContaCriada.isPresent()) {

                if(contaNielsen.get().getSaldo() >= valorTransferencia) {

                    contaNielsen.get().setSaldo(contaNielsen.get().getSaldo() - valorTransferencia);
                    novaContaCriada.get().setSaldo(novaContaCriada.get().getSaldo() + valorTransferencia);

                    contaRepository.save(contaNielsen.get());
                    contaRepository.save(novaContaCriada.get());

                    var transferencia = new Movimentacao(TipoMovimentacao.TRANSFERENCIA, contaNielsen.get(), novaContaCriada.get(), valorTransferencia, contaNielsen.get().getSaldo());
                    movimentacaoRepository.save(transferencia);
                } else {
                    throw new SaldoInsuficienteException("saldo insuficiente na conta id:" + contaNielsen.get().getId() + ". Saldo: " + contaNielsen.get().getSaldo());
                }
            }
        }
    }
}
