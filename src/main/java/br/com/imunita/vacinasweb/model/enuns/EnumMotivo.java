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
public enum EnumMotivo {

    VENCIMENTO("Vencimento", "V"),
    FALTA("Falta", "F"),
    QUEBRA("Quebra", "Q");

    private String descricao;

    private String valor;

    private EnumMotivo(String descricao, String valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
