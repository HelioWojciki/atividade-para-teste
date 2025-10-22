package com.empresa.gestao.runners;

import com.empresa.gestao.domain.Departamento;
import com.empresa.gestao.domain.Funcionario;
import com.empresa.gestao.service.AplicarBonusService;
import com.empresa.gestao.service.DepartamentoService;
import com.empresa.gestao.service.FuncionarioService;
import com.empresa.gestao.util.ExtrairDados;
import com.empresa.gestao.util.LeitorArquivo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Set;

@Component
public class ProcessadorDeArquivo implements CommandLineRunner {

    private final DepartamentoService departamentoService;
    private final FuncionarioService funcionarioService;
    private final ExtrairDados extrairDados;
    private final LeitorArquivo leitorArquivo;
    private final AplicarBonusService aplicarBonusService;

    public ProcessadorDeArquivo(
            DepartamentoService departamentoService,
            FuncionarioService funcionarioService,
            ExtrairDados extrairDados,
            LeitorArquivo leitorArquivo,
            AplicarBonusService aplicarBonusService) {
        this.departamentoService = departamentoService;
        this.funcionarioService = funcionarioService;
        this.extrairDados = extrairDados;
        this.leitorArquivo = leitorArquivo;
        this.aplicarBonusService = aplicarBonusService;
    }

    @Override
    public void run(String... args) throws Exception {
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

        aplicarBonusService.aplicarBonusVendedores(departamentoCampeao.getVendedores());
    }

}