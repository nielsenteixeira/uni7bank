package com.bank.uni7bank.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@TypeDef(name = "tipo_pessoa", typeClass = PostgreSQLEnumType.class)
public class Cliente extends BaseEntity {
    private String nome;
    private String cpfCnpj;

    @Enumerated(STRING)
    @Column(columnDefinition = "tipo_pessoa")
    @Type(type = "tipo_pessoa")
    private TipoPessoa tipoPessoa;

    @OneToMany(mappedBy="cliente", fetch = FetchType.EAGER)
    private List<Conta> contas;

    public Cliente() {
    }

    public Cliente(String nome, String cpfCnpj, TipoPessoa tipoPessoa) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.tipoPessoa = tipoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
