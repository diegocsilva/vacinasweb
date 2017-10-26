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
public class ConsultarCidadeMBean extends GenericListController implements Serializable {

    private Cidade cidade;
    private Estado estadoSelecionado;

    private List<Cidade> listaCidade;
    private List<Estado> listaEstados;

    @Inject
    private CidadeService service;

    @Inject
    private EstadoService estadoService;

    /**
     * Creates a new instance of cidadeMBean
     */
    public ConsultarCidadeMBean() {
    }

    @PostConstruct
    public void init() {
        if (cidade == null) {
            cidade = new Cidade();
            estadoSelecionado = new Estado();
        }

        listaCidade = new ArrayList<>();
        listaEstados = new ArrayList<>();

        listaEstados.addAll(estadoService.getListaTodosEstados());
        listaCidade.addAll(service.getListaTodasCidades());
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
    public void filtrar() {
        this.cidade.setEstado(getEstadoInListaEstadosByIdEstado(cidade.getEstado().getIdEstado()));
        this.listaCidade = this.service.getListByFilters(cidade);
    }

    public Estado getEstadoInListaEstadosByIdEstado(Integer idEstado) {
        if (idEstado == null) {
            return new Estado();
        } else {
            for (Estado estado : this.listaEstados) {
                if (estado.getIdEstado().equals(idEstado)) {
                    return estado;
                }
            }
        }
        return null;
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "cidade");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarCidade((Cidade) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Cidade. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarCidadeMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
        this.cidade = (Cidade) objeto;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-CIDADE-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-CIDADE-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-CIDADE-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-CIDADE-DELETAR");
    }

    /**
     * #################################################################
     */
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;

    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public CidadeService getService() {
        return service;
    }

    public void setService(CidadeService service) {
        this.service = service;
    }

    public EstadoService getEstadoService() {
        return estadoService;
    }

    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public Estado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }
}
