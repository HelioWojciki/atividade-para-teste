package com.empresa.gestao.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

@Component
public class LeitorArquivo {

    public String lerArquivo(Path caminho){
        StringBuilder conteudoArquivo = new StringBuilder();

        try (FileReader fileReader = new FileReader(String.valueOf(caminho));
             BufferedReader bufferedReader = new BufferedReader(fileReader)  ){

            String linha;
            while ((linha = bufferedReader.readLine()) != null){
                if (!linha.isEmpty()){
                    conteudoArquivo.append(linha).append(" ");
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return conteudoArquivo.toString().trim();
    }
}
