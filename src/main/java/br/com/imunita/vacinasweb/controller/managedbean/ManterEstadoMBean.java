/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EstadoService;
import br.com.imunita.vacinasweb.model.entity.Estado;
import java.io.Serializable;
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
public class ManterEstadoMBean extends GenericFormController implements Serializable {

    private Estado estado;

    @Inject
    private EstadoService service;

    /**
     * Creates a new instance of ManterEstadoMBean
     */
    public ManterEstadoMBean() {
    }

    @PostConstruct
    public void init() {
        this.estado = (Estado) facesProducer.getFlash("estado");
        if (estado == null) {
            estado = new Estado();
        }
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
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public String salvar() {
        try {
            this.service.salvarEstado(estado);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Estado! Erro: " + ex.getMessage());
            Logger.getLogger(ManterEstadoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-ESTADO-NOVO");
    }

    @Override
    public String novo() {
        this.estado = new Estado();
        return getNomeTelaManter();
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-ESTADO-DELETAR");
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarEstado(estado);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Estado. Erro: " + ex.getMessage());
            Logger.getLogger(ManterEstadoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    /**
     * *
     * Getter and Setter
     */
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstadoService getService() {
        return service;
    }

    public void setService(EstadoService service) {
        this.service = service;
    }
}
