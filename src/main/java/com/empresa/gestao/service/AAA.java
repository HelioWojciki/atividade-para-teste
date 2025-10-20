package com.empresa.gestao.service;

import com.empresa.gestao.domain.Departamento;
import com.empresa.gestao.domain.Vendedor;

import java.io.*;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AAA {
    public static void main(String[] args) {
        Path path = Path.of("C:/Users/helio/OneDrive/Área de Trabalho/gestao/src/main/java/com/empresa/gestao/doc", "teste.txt");
        StringBuilder sb = new StringBuilder();

        try (   FileReader fileReader = new FileReader(String.valueOf(path));
                BufferedReader bufferedReader = new BufferedReader(fileReader)  ){

            String linha;
            while ((linha = bufferedReader.readLine()) != null){
                String linhaTratada = linha;
                if (!linhaTratada.isEmpty()){
                    sb.append(linhaTratada);
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        String dadosCompletos = sb.toString().trim();

        String regex = "Nome\\s(.*?)Departamento\\s(.*?)\\sCodigo\\s(.*?)\\sCargo\\s(.*?)\\s(?:Venda\\s(.*?)\\s)?Salario\\s(.*?)\\s";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dadosCompletos);

        System.out.println("Olha o que vem completo: ." + dadosCompletos + ".\n\n");
        while (matcher.find()){
            String nome = matcher.group(1).trim();
            String departamento = matcher.group(2).trim();
            String codigo = matcher.group(3).trim();
            String cargo = matcher.group(4).trim();

            double venda = matcher.group(5) != null ? Double.parseDouble(matcher.group(4).trim()) : 0.0;
            double salario = matcher.group(6) != null ? Double.parseDouble(matcher.group(5).trim()) : 0.0;



        }


    }
}













