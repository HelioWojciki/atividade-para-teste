package com.empresa.gestao.service;

import com.empresa.gestao.business.AplicarBonus;
import com.empresa.gestao.domain.Departamento;
import com.empresa.gestao.domain.Funcionario;
import com.empresa.gestao.domain.Gerente;
import com.empresa.gestao.domain.Vendedor;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessadorDeArquivo {
    public static void main(String[] args) {
        Path caminho = Path.of("C:/Users/helio/OneDrive/Área de Trabalho/gestao/src/main/java/com/empresa/gestao/doc", "teste.txt");
        String dadosCompletos = LeitorArquivo.lerArquivo(caminho);

//        regex de busca dep
        Set<Departamento> departamentosEncontrados = ExtrairDados.extrairDepartamentos(dadosCompletos);

//        regex de busca func
        String regexFunc = "Nome\\s(.*?)Departamento\\s(.*?)\\sCargo\\s(.*?)\\s(?:Venda\\s(.*?)\\s)?Salario\\s(.*?)(?:\\s|$)";
        Pattern patternFunc = Pattern.compile(regexFunc);
        Matcher matcherFunc = patternFunc.matcher(dadosCompletos);

        Set<Funcionario> funcionariosEncontrados = new HashSet<>();

        while (matcherFunc.find()){
            String nome = matcherFunc.group(1).trim();
            String departamento = matcherFunc.group(2).trim();
            String cargo = matcherFunc.group(3).trim();

            double venda = matcherFunc.group(4) != null ? Double.parseDouble(matcherFunc.group(4).trim()) : 0.0;
            double salario = matcherFunc.group(5) != null ? Double.parseDouble(matcherFunc.group(5).trim()) : 0.0;

            for (Departamento dep : departamentosEncontrados) {

                if (dep.getNome().equalsIgnoreCase(departamento)){
                    if (cargo.equalsIgnoreCase("Gerente")){
                        Gerente gerente = new Gerente(nome, cargo, salario, dep);
                        funcionariosEncontrados.add(gerente);;
                    }
                    if (cargo.equalsIgnoreCase("Vendedor")){
                        Vendedor vendedor = new Vendedor(nome, cargo, salario, dep, venda);
                        funcionariosEncontrados.add(vendedor);
                        dep.addFunc(vendedor);
                    }
                }
            }

        }


        Departamento departamentoCampeaoEncontrado = calcularVendasDep(departamentosEncontrados);

        AplicarBonus aplicarBonus = new AplicarBonus();

        for (Vendedor vendedor : departamentoCampeaoEncontrado.getVendedores()) {
            aplicarBonus.aplicarBonusDestaque(vendedor);
            System.out.println("Aplicado bônus no vendedor: " + vendedor.getNome() + " com sucesso!");
        }

    }

    public static Departamento calcularVendasDep(Set<Departamento> departamentos){
        Departamento departamentoCampeao = null;

        for (Departamento departamento : departamentos) {
            double vendas = 0.0;
            double vencedor = 0.0;

            for (Vendedor vendedor : departamento.getVendedores()) {
                vendas += vendedor.getVenda();
            }
            System.out.println("Total de vendas do Departamento: " + departamento.getNome() + " R$: " + vendas);
            if (vendas > vencedor){
                vencedor = vendas;
                departamentoCampeao = departamento;
            }
        }
        if (departamentoCampeao != null){
            departamentoCampeao.setCampeao(true);
            System.out.println("DEPARTAMENTO CAMPEÃO FOI: " + departamentoCampeao.getNome());
        }
        return departamentoCampeao;
    }



}









