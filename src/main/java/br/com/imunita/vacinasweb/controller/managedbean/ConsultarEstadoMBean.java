/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EstadoService;
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
 * @author Diego
 */
@ManagedBean
@ViewScoped
public class ConsultarEstadoMBean extends GenericListController implements Serializable {

    private Estado estado;
    private Estado estadoSelecionado;

    private List<Estado> listaEstado;

    @Inject
    private EstadoService service;

    /**
     * Creates a new instance of estadoMBean
     */
    public ConsultarEstadoMBean() {
    }

    @PostConstruct
    public void init() {
        this.estado = new Estado();

        listaEstado = new ArrayList<>();
        listaEstado.addAll(service.getListaTodosEstados());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterEstado";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarEstado";
    }

    @Override
    public void filtrar() {
        this.listaEstado = service.getListByFilters(this.estado);
    }

    @Override
    public String novo() {
        this.estado = new Estado();
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "estado");
        return getNomeTelaManter();
    }

    @Override
    public String deletarVarios() {
        // TODO: Deve haver um metodo para deletar todos os registros selecionados 
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
        this.estadoSelecionado = (Estado) objeto;
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarEstado((Estado) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Estado. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarEstadoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-ESTADO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-ESTADO-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-ESTADO-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-ESTADO-DELETAR");
    }

    /**
     * #############################################################
     */
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getListaEstados() {
        return listaEstado;
    }

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public EstadoService getService() {
        return service;
    }

    public void setService(EstadoService service) {
        this.service = service;
    }

    public Estado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

}
