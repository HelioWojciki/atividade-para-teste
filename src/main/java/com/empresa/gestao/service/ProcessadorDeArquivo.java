package com.empresa.gestao.service;

import com.empresa.gestao.business.AplicarBonus;
import com.empresa.gestao.domain.Departamento;
import com.empresa.gestao.domain.Funcionario;
import com.empresa.gestao.domain.Vendedor;

import java.nio.file.Path;
import java.util.Set;

public class ProcessadorDeArquivo {

    public static void main(String[] args) {

        DepartamentoService departamentoService = new DepartamentoService();
        FuncionarioService funcionarioService = new FuncionarioService();
        ExtrairDados extrairDados = new ExtrairDados();
        LeitorArquivo leitorArquivo = new LeitorArquivo();
        AplicarBonus aplicarBonus = new AplicarBonus();

        Path caminho = Path.of("C:/Users/helio/OneDrive/Área de Trabalho/gestao/src/main/java/com/empresa/gestao/doc", "teste.txt");

        String dadosCompletos = leitorArquivo.lerArquivo(caminho);
        Set<Departamento> departamentos = extrairDados.extrairDepartamentos(dadosCompletos);
        Set<Funcionario> funcionarios = extrairDados.extrairFuncionarios(departamentos, dadosCompletos);

        System.out.println("Departamentos: ");
        departamentoService.imprimirDepartamento(departamentos);
        System.out.println("\nFuncionários: ");
        funcionarioService.imprimirFuncionarios(funcionarios);

        System.out.println("-------------------------------------");

        Departamento departamentoCampeao = departamentoService.calcularVendasDepartamentoCampeao(departamentos);


        AplicarBonusService.aplicarBonusVendedores(departamentoCampeao.getVendedores());

    }



}









