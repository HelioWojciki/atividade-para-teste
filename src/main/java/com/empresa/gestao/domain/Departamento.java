package com.empresa.gestao.domain;

import java.util.*;


public class Departamento {
    private String nome;
    private int codigo;
    private Set<Vendedor> vendedores = new HashSet<>();
    private boolean campeao = false;

    public Departamento(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void addFunc(Vendedor vendedor){
        vendedores.add(vendedor);
    }

    public Set<Vendedor> getVendedores() {
        return vendedores;
    }

    public boolean isCampeao() {
        return campeao;
    }

    public void setCampeao(boolean campeao) {
        this.campeao = campeao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }
}
