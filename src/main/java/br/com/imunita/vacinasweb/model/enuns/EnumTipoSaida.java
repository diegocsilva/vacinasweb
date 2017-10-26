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
public enum EnumTipoSaida {

    VENDA("Venda", "V"),
    APLICACAO("Aplicação", "A"),
    PERDA("Perda", "P");

    private String Descricao;

    private String valor;

    private EnumTipoSaida(String Descricao, String valor) {
        this.Descricao = Descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }

}
