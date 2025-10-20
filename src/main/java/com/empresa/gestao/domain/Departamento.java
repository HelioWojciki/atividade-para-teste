package com.empresa.gestao.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Departamento {
    private String nome;
    private int codigo;
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Departamento(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }
}
