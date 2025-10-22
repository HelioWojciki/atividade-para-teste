package com.empresa.gestao.service;

import com.empresa.gestao.domain.Funcionario;

import java.util.Set;
import java.util.stream.Collectors;

public class FuncionarioService {

    public Set<String> getNomes(Set<Funcionario> funcionarios){
        return funcionarios.stream()
                .map(Funcionario::getNome)
                .collect(Collectors.toSet());
    }

    public void imprimirFuncionarios (Set<Funcionario> funcionarios) {
        funcionarios.forEach(e -> System.out.println(e.getNome()));
    }

}
