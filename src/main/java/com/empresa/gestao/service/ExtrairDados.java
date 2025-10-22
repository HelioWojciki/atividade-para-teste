package com.empresa.gestao.service;

import com.empresa.gestao.domain.Departamento;
import com.empresa.gestao.domain.Funcionario;
import com.empresa.gestao.domain.Gerente;
import com.empresa.gestao.domain.Vendedor;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtrairDados {

    public Set<Departamento> extrairDepartamentos(String texto) {
        String regexDept = "Departamento\\s*(.*?)\\s*Codigo\\s*(\\d+)";
        Pattern patternDept = Pattern.compile(regexDept);
        Matcher matcherDept = patternDept.matcher(texto);

        Set<Departamento> departamentos = new HashSet<>();

        while (matcherDept.find()) {
            String nomeDepartamento = matcherDept.group(1).trim();
            int codigo = Integer.parseInt(matcherDept.group(2).trim());

            departamentos.add(new Departamento(nomeDepartamento, codigo));
        }
        return departamentos;
    }

    public Set<Funcionario> extrairFuncionarios(Set<Departamento> departamentos, String texto){
        String regexFunc = "Nome\\s(.*?)Departamento\\s(.*?)\\sCargo\\s(.*?)\\s(?:Venda\\s(.*?)\\s)?Salario\\s(.*?)(?:\\s|$)";
        Pattern patternFunc = Pattern.compile(regexFunc);
        Matcher matcherFunc = patternFunc.matcher(texto);

        Set<Funcionario> funcionarios = new HashSet<>();

        while (matcherFunc.find()){
            String nome = matcherFunc.group(1).trim();
            String departamento = matcherFunc.group(2).trim();
            String cargo = matcherFunc.group(3).trim();

            double venda = matcherFunc.group(4) != null ? Double.parseDouble(matcherFunc.group(4).trim()) : 0.0;
            double salario = matcherFunc.group(5) != null ? Double.parseDouble(matcherFunc.group(5).trim()) : 0.0;

            for (Departamento dep : departamentos) {

                if (dep.getNome().equalsIgnoreCase(departamento)){
                    if (cargo.equalsIgnoreCase("Gerente")){
                        Gerente gerente = new Gerente(nome, cargo, salario, dep);
                        funcionarios.add(gerente);
                    }
                    if (cargo.equalsIgnoreCase("Vendedor")){
                        Vendedor vendedor = new Vendedor(nome, cargo, salario, dep, venda);
                        funcionarios.add(vendedor);
                        dep.addFunc(vendedor);
                    }
                }
            }

        }
        return funcionarios;
    }
}
