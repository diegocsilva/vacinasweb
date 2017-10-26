/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.wrapper;

import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.util.Date;

/**
 *
 * @author rodolpho.sotolani
 */
public class LoteVacinaWrapper {

    private Vacina vacina;
    private Empresa fabricante;
    private Date dataValidadeInicial;
    private Date dataValidadeFinal;
    private Date dataFabricacaoInicial;
    private Date dataFabricacaoFinal;
    private String numeroLote;

    public LoteVacinaWrapper() {
        vacina = new Vacina();
        fabricante = new Empresa();
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Empresa getFabricante() {
        return fabricante;
    }

    public void setFabricante(Empresa fabricante) {
        this.fabricante = fabricante;
    }

    public Date getDataValidadeInicial() {
        return dataValidadeInicial;
    }

    public void setDataValidadeInicial(Date dataValidadeInicial) {
        this.dataValidadeInicial = dataValidadeInicial;
    }

    public Date getDataValidadeFinal() {
        return dataValidadeFinal;
    }

    public void setDataValidadeFinal(Date dataValidadeFinal) {
        this.dataValidadeFinal = dataValidadeFinal;
    }

    public Date getDataFabricacaoInicial() {
        return dataFabricacaoInicial;
    }

    public void setDataFabricacaoInicial(Date dataFabricacaoInicial) {
        this.dataFabricacaoInicial = dataFabricacaoInicial;
    }

    public Date getDataFabricacaoFinal() {
        return dataFabricacaoFinal;
    }

    public void setDataFabricacaoFinal(Date dataFabricacaoFinal) {
        this.dataFabricacaoFinal = dataFabricacaoFinal;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

}
