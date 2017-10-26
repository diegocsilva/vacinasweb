/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.enuns;

/**
 *
 * @author Rodolpho
 */
public enum EnumSexo {

    MASCULINO('M', "Masculino"),
    FEMININO('F', "Feminino");

    private char sigla;
    private String descricao;

    public char getSigla() {
        return sigla;
    }

    public void setSigla(char sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private EnumSexo(char sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.valueOf(sigla);
    }
}
