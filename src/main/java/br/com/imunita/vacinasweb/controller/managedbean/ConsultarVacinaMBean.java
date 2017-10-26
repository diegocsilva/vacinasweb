/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EmpresaService;
import br.com.imunita.vacinasweb.controller.service.VacinaEmpresaService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import br.com.imunita.vacinasweb.model.wrapper.vacinaWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class ConsultarVacinaMBean extends GenericListController implements Serializable {

    private List<Vacina> listaVacinas;

    //Filtros
    private vacinaWrapper wrapper;
    private List<Empresa> listaFabricantes;
    private List<Empresa> listaFornecedores;

    @Inject
    private VacinaService service;

    @Inject
    private EmpresaService empresaService;

    @Inject
    private VacinaEmpresaService vacinaEmpresaService;

    /**
     * Creates a new instance of ConsultarVacinaMBean
     */
    public ConsultarVacinaMBean() {
    }

    @PostConstruct
    public void init() {
        this.wrapper = new vacinaWrapper();

        this.listaVacinas = new ArrayList<>();
        this.listaFabricantes = new ArrayList<>();
        this.listaFornecedores = new ArrayList<>();

        this.listaVacinas.addAll(this.service.getListaTodosVacinas());
        this.listaFabricantes.addAll(this.empresaService.getListaTodasEmpresasFabricantes());
        this.listaFornecedores.addAll(this.empresaService.getListaTodasEmpresasFornecedoras());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarVacina";
    }

    @Override
    public void filtrar() {
        this.listaVacinas = this.service.getListaVacinas(wrapper);
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "vacina");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarVacina((Vacina) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Vacina. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-VACINA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-VACINA-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-VACINA-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-VACINA-DELETAR");
    }

    /**
     * #################################################################################
     */
    public List<Vacina> getListaVacinas() {
        return listaVacinas;
    }

    public void setListaVacinas(List<Vacina> listaVacinas) {
        this.listaVacinas = listaVacinas;
    }

    public List<Empresa> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Empresa> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }

    public List<Empresa> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Empresa> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public VacinaService getService() {
        return service;
    }

    public void setService(VacinaService service) {
        this.service = service;
    }

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public VacinaEmpresaService getVacinaEmpresaService() {
        return vacinaEmpresaService;
    }

    public void setVacinaEmpresaService(VacinaEmpresaService vacinaEmpresaService) {
        this.vacinaEmpresaService = vacinaEmpresaService;
    }

    public vacinaWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(vacinaWrapper wrapper) {
        this.wrapper = wrapper;
    }

}
