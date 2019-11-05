package com.unisinos.trabalho.packages.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * • número do CPF (numérico);
 * • número do RG (numérico);
 * • nome da pessoa (string);
 * • data de nascimento da pessoa (data no formato DD/MM/AAAA) e
 * • nome da cidade onde a pessoa nasceu (string).
 */
public class Person {

    public Person(String cpf, String rg, String name, LocalDate dataNascimento, String cidadeNascimento) {
        this.cpf = cpf;
        this.rg = rg;
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.cidadeNascimento = cidadeNascimento;
    }

    @NotNull
    private String cpf;

    @NotNull
    private String rg;

    @NotNull
    private String name;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private String cidadeNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }
}
