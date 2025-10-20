package com.empresa.gestao.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gerente extends Funcionario {

    public Gerente(String nome, String cargo, double salario, Departamento departamento) {
        super(nome, cargo, salario, departamento);
    }


}
