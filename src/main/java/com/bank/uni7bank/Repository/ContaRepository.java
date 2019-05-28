package com.bank.uni7bank.Repository;

import com.bank.uni7bank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByClienteId(long clienteId);
}
