/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.TipoFuncionarioService;
import br.com.imunita.vacinasweb.model.entity.TipoFuncionario;
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
public class ConsultarTipoFuncionarioMBean extends GenericListController implements Serializable {

    private TipoFuncionario tipoFuncionario;
    private List<TipoFuncionario> listaTipoFuncionarios;
    private List<TipoFuncionario> listaTipoFuncionariosSelecionados;

    @Inject
    private TipoFuncionarioService service;

    /**
     * Creates a new instance of ConsultarTipoFuncionario
     */
    public ConsultarTipoFuncionarioMBean() {
    }

    @PostConstruct
    public void init() {
        if (this.tipoFuncionario == null) {
            this.tipoFuncionario = new TipoFuncionario();
        }
        this.listaTipoFuncionarios = this.service.getListaTodosTipoFuncionarios();
        this.listaTipoFuncionariosSelecionados = new ArrayList<>();
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterTipoFuncionario";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarTipoFuncionario";
    }

    @Override
    public void filtrar() {
        this.listaTipoFuncionarios = this.service.getTipoFuncionarios(this.tipoFuncionario);
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "tipoFuncionario");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarTipoFuncionario((TipoFuncionario) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Tipo de Funcionário. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarTipoFuncionarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {

        for (TipoFuncionario tipoFuncionarioSelect : listaTipoFuncionariosSelecionados) {
            this.deletar(tipoFuncionarioSelect);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-TIPOFUNCIONARIO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-TIPOFUNCIONARIO-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-TIPOFUNCIONARIO-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-TIPOFUNCIONARIO-DELETAR");
    }

    /**
     * *
     * ################################################################################
     */
    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public List<TipoFuncionario> getListaTipoFuncionarios() {
        return listaTipoFuncionarios;
    }

    public void setListaTipoFuncionarios(List<TipoFuncionario> listaTipoFuncionarios) {
        this.listaTipoFuncionarios = listaTipoFuncionarios;
    }

    public List<TipoFuncionario> getListaTipoFuncionariosSelecionados() {
        return listaTipoFuncionariosSelecionados;
    }

    public void setListaTipoFuncionariosSelecionados(List<TipoFuncionario> listaTipoFuncionariosSelecionados) {
        this.listaTipoFuncionariosSelecionados = listaTipoFuncionariosSelecionados;
    }

    public TipoFuncionarioService getService() {
        return service;
    }

    public void setService(TipoFuncionarioService service) {
        this.service = service;
    }

}
