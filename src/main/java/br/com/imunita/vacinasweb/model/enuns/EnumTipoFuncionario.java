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
public enum EnumTipoFuncionario {

    MEDICO("Médico"),
    ENFERMEIRO("Enfermeira"),
    RECEPCIONISTA("Recepcionista");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private EnumTipoFuncionario(String descricao) {
        this.descricao = descricao;
    }

}
