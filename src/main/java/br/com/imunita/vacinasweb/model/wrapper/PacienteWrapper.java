/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.wrapper;

import java.util.Date;

/**
 *
 * @author Rodolpho
 */
public class PacienteWrapper {

    private String nome;
    private String nomeMae;
    private String nomeResponsavel;
    private String cpf;
    private char[] sexo;
    private Date dataNascimentoInicial;
    private Date dataNascimentoFinal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Date getDataNascimentoInicial() {
        return dataNascimentoInicial;
    }

    public void setDataNascimentoInicial(Date dataNascimentoInicial) {
        this.dataNascimentoInicial = dataNascimentoInicial;
    }

    public Date getDataNascimentoFinal() {
        return dataNascimentoFinal;
    }

    public void setDataNascimentoFinal(Date dataNascimentoFinal) {
        this.dataNascimentoFinal = dataNascimentoFinal;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char[] getSexo() {
        return sexo;
    }

    public void setSexo(char[] sexo) {
        this.sexo = sexo;
    }
}
