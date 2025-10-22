package com.empresa.gestao.service;

import com.empresa.gestao.domain.Departamento;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtrairDados {

    public static Set<Departamento> extrairDepartamentos(String texto) {
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
}
