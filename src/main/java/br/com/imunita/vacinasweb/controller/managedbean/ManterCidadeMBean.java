/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.CidadeService;
import br.com.imunita.vacinasweb.controller.service.EstadoService;
import br.com.imunita.vacinasweb.model.entity.Cidade;
import br.com.imunita.vacinasweb.model.entity.Estado;
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
public class ManterCidadeMBean extends GenericFormController implements Serializable {

    private Cidade cidade;

    private List<Estado> listaEstados;

    @Inject
    private CidadeService service;

    @Inject
    private EstadoService estadoService;

    /**
     * Creates a new instance of ManterCidadeMBean
     */
    public ManterCidadeMBean() {
    }

    @PostConstruct
    public void init() {
        this.cidade = (Cidade) facesProducer.getFlash("cidade");
        if (cidade == null) {
            cidade = new Cidade();
        }

        listaEstados = new ArrayList<>();
        listaEstados.addAll(estadoService.getListaTodosEstados());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterCidade";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarCidade";
    }

    @Override
    public String salvar() {
        try {
            this.service.salvarCidade(cidade);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Cidade! Erro: " + ex.getMessage());
            Logger.getLogger(ManterCidadeMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarCidade(cidade);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Cidade. Erro: " + ex.getMessage());
            Logger.getLogger(ManterCidadeMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-CIDADE-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        if (cidade.getIdCidade() == null) {
            return false;
        }
        return seguranca.hasPermission("MANTER-CIDADE-DELETAR");
    }

    /**
     * ###############################################################
     */
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public CidadeService getService() {
        return service;
    }

    public void setService(CidadeService service) {
        this.service = service;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public EstadoService getEstadoService() {
        return estadoService;
    }

    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }
}
