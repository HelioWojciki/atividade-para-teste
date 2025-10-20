package com.empresa.gestao.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuloBonus {

    public static void main(String[] args) {

        Path path = Path.of("C:/Users/helio/OneDrive/Área de Trabalho/gestao/src/main/java/com/empresa/gestao/doc", "teste.txt");

        StringBuilder sb = new StringBuilder();

        try (FileReader fileReader = new FileReader(String.valueOf(path));
             BufferedReader bufferedReader = new BufferedReader(fileReader))    {

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String linhaTratada = linha.trim();
                if (!linhaTratada.isEmpty()) {
                    sb.append(linhaTratada).append(" ");
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        String dadosCompletos = sb.toString();

        String regexFuncionario = "Nome\\s(.*?)\\sDepartamento\\s(.*?)\\sCargo\\s(.*?)\\s(?:Venda\\s(.*?)\\s)?Salario\\s(.*?)\\s";

        Pattern pattern = Pattern.compile(regexFuncionario);
        Matcher matcher = pattern.matcher(dadosCompletos);


        System.out.println("--- EXTRAÇÃO DE DADOS INICIADA ---");

        while (matcher.find()) {

            String nome = matcher.group(1).trim();
            String departamento = matcher.group(2).trim();
            String cargo = matcher.group(3).trim();

            String vendaStr = matcher.group(4) != null ? matcher.group(4).trim() : "0";
            String salarioStr = matcher.group(5).trim();

            try {
                double venda = Double.parseDouble(vendaStr);
                double salario = Double.parseDouble(salarioStr);


                System.out.println("\n-------------------------------------");
                System.out.println("Funcionario Encontrado:");
                System.out.println("  Nome: " + nome);
                System.out.println("  Departamento: " + departamento);
                System.out.println("  Cargo: " + cargo);
                System.out.println("  Venda: " + venda);
                System.out.println("  Salário: " + salario);

            } catch (NumberFormatException e) {
                System.err.println("ERRO: Não foi possível converter Venda ou Salário para número para o funcionário " + nome);
            }
        }

        String regexDepartamentoCodigo = "Departamento\\s(.*?)\\sCodigo\\s(.*?)\\s";
        Pattern patternDept = Pattern.compile(regexDepartamentoCodigo);
        Matcher matcherDept = patternDept.matcher(dadosCompletos);

        System.out.println("\n--- CÓDIGOS DE DEPARTAMENTO ---");
        while (matcherDept.find()) {
            String nomeDepartamento = matcherDept.group(1).trim();
            String codigo = matcherDept.group(2).trim();

            System.out.println("  " + codigo + " -> " + nomeDepartamento);
        }
    }
}