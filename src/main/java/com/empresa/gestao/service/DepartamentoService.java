package com.empresa.gestao.service;

import com.empresa.gestao.domain.Departamento;
import com.empresa.gestao.domain.Vendedor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    public static Set<String> getNome(Set<Departamento> departamentos) {
        return departamentos.stream()
                .map(Departamento::getNome)
                .collect(Collectors.toSet());
    }

    public void imprimirDepartamento(Set<Departamento> departamentos) {
        departamentos.forEach(e -> System.out.println(e.getNome()));
    }

    public Departamento calcularVendasDepartamentoCampeao(Set<Departamento> departamentos){
        Departamento departamentoCampeao = null;
        double maiorVenda = 0.0;

        for (Departamento dep : departamentos) {
            double totalVendas = dep.getVendedores()
                    .stream()
                    .mapToDouble(Vendedor::getVenda)
                    .sum();

            if (totalVendas > maiorVenda){
                departamentoCampeao = dep;
            }
        }

        if (departamentoCampeao != null){
            departamentoCampeao.setCampeao(true);
        }
        return departamentoCampeao;
    }

}
