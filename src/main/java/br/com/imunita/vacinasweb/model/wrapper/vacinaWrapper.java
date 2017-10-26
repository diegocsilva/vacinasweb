/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.wrapper;

import br.com.imunita.vacinasweb.model.entity.Empresa;

/**
 *
 * @author Rodolpho
 */
public class vacinaWrapper {

    private String nomeVacina;
    private Empresa fabricante;
    private Empresa fornecedor;
    private char[] usoAdulto;
    private boolean usoAdultoSim;
    private boolean usoAdultoNao;
    private char[] usoInfantil;
    private boolean usoInfantilSim;
    private boolean usoInfantilNao;

    public vacinaWrapper() {

        fornecedor = new Empresa();
        fabricante = new Empresa();
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public Empresa getFabricante() {
        return fabricante;
    }

    public void setFabricante(Empresa fabricante) {
        this.fabricante = fabricante;
    }

    public Empresa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Empresa fornecedor) {
        this.fornecedor = fornecedor;
    }

    public char[] getUsoAdulto() {
        return usoAdulto;
    }

    public void setUsoAdulto(char[] usoAdulto) {
        this.usoAdulto = usoAdulto;
    }

    public boolean isUsoAdultoSim() {
        if (usoAdulto.length > 0 && usoAdulto[0] == 'S') {
            return true;
        }
        if (usoAdulto.length > 1 && usoAdulto[1] == 'S') {
            return true;
        }
        return usoAdultoSim;
    }

    public void setUsoAdultoSim(boolean usoAdultoSim) {
        this.usoAdultoSim = usoAdultoSim;
    }

    public boolean isUsoAdultoNao() {
        if (usoAdulto.length > 0 && usoAdulto[0] == 'N') {
            return true;
        }
        if (usoAdulto.length > 1 && usoAdulto[1] == 'N') {
            return true;
        }
        return usoAdultoNao;
    }

    public void setUsoAdultoNao(boolean usoAdultoNao) {
        this.usoAdultoNao = usoAdultoNao;
    }

    public char[] getUsoInfantil() {
        return usoInfantil;
    }

    public void setUsoInfantil(char[] usoInfantil) {
        this.usoInfantil = usoInfantil;
    }

    public boolean isUsoInfantilSim() {
        if (usoInfantil.length > 0 && usoInfantil[0] == 'S') {
            return true;
        }
        if (usoInfantil.length > 1 && usoInfantil[1] == 'S') {
            return true;
        }
        return usoInfantilSim;
    }

    public void setUsoInfantilSim(boolean usoInfantilSim) {
        this.usoInfantilSim = usoInfantilSim;
    }

    public boolean isUsoInfantilNao() {
        if (usoInfantil.length > 0 && usoInfantil[0] == 'N') {
            return true;
        }
        if (usoInfantil.length > 1 && usoInfantil[1] == 'N') {
            return true;
        }
        return usoInfantilNao;
    }

    public void setUsoInfantilNao(boolean usoInfantilNao) {
        this.usoInfantilNao = usoInfantilNao;
    }

}
