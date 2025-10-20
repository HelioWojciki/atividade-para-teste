package com.empresa.gestao.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vendedor extends Funcionario{
    private double venda;

    public Vendedor(String nome, String cargo, double salario, Departamento departamento, double venda) {
        super(nome, cargo, salario, departamento);
        this.venda = venda;
    }
}
