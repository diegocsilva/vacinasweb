/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EmpresaService;
import br.com.imunita.vacinasweb.controller.service.LoteVacinaService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import br.com.imunita.vacinasweb.model.wrapper.LoteVacinaWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author rodolpho.sotolani
 */
@Named(value = "consultarLoteVacinaMBean")
@ViewScoped
public class ConsultarLoteVacinaMBean extends GenericListController implements Serializable {

    //Filtros
    private LoteVacinaWrapper loteVacina;

    //Listagem
    private List<LoteVacina> listaLoteVacina;
    private List<Vacina> listaVacina;
    private List<Empresa> listaFabricantes;

    @Inject
    private LoteVacinaService service;

    @Inject
    private VacinaService vacinaService;

    @Inject
    private EmpresaService empresaService;

    /**
     * Creates a new instance of ConsultarLoteVacinaMBean
     */
    public ConsultarLoteVacinaMBean() {
    }

    @PostConstruct
    public void init() {

        loteVacina = new LoteVacinaWrapper();

        listaLoteVacina = new ArrayList<>();
        listaVacina = new ArrayList<>();
        listaFabricantes = new ArrayList<>();

        this.listaLoteVacina.addAll(this.service.getListaTodosLotesVacina());
        this.listaFabricantes.addAll(this.empresaService.getListaTodasEmpresas());
        this.listaVacina.addAll(this.vacinaService.getListaTodosVacinas());
    }

    @Override
    public void filtrar() {
        this.listaLoteVacina = this.service.getListaTodosLotesVacina();
    }

    @Override
    public String novo() {
        facesProducer.putFlash(new LoteVacina(), "loteVacina");
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "loteVacina");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarLoteVacina((LoteVacina) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Lote de Vacina. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarLoteVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-LOTEVACINA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-LOTEVACINA-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-LOTEVACINA-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-LOTEVACINA-DELETAR");
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterLoteVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarLoteVacina";
    }

    /**
     * ########################################################################## @return
     */
    public LoteVacinaWrapper getLoteVacina() {
        return loteVacina;
    }

    public void setLoteVacina(LoteVacinaWrapper loteVacina) {
        this.loteVacina = loteVacina;
    }

    public List<LoteVacina> getListaLoteVacina() {
        return listaLoteVacina;
    }

    public void setListaLoteVacina(List<LoteVacina> listaLoteVacina) {
        this.listaLoteVacina = listaLoteVacina;
    }

    public LoteVacinaService getService() {
        return service;
    }

    public void setService(LoteVacinaService service) {
        this.service = service;
    }

    public List<Vacina> getListaVacina() {
        return listaVacina;
    }

    public void setListaVacina(List<Vacina> listaVacina) {
        this.listaVacina = listaVacina;
    }

    public VacinaService getVacinaService() {
        return vacinaService;
    }

    public void setVacinaService(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    public List<Empresa> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Empresa> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }
}
