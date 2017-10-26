/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.wrapper;

import br.com.imunita.vacinasweb.model.entity.TipoFuncionario;

/**
 *
 * @author rodolpho.sotolani
 */
public class FuncionarioWrapper {

    private String nome;

    private TipoFuncionario tipoFuncionario;

    private char[] situacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public char[] getSituacao() {
        return situacao;
    }

    public void setSituacao(char[] situacao) {
        this.situacao = situacao;
    }

}
