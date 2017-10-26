/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.TipoFuncionarioService;
import br.com.imunita.vacinasweb.model.entity.TipoFuncionario;
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
public class ManterTipoFuncionarioMBean extends GenericFormController implements Serializable {

    private TipoFuncionario tipoFuncionario;

    @Inject
    private TipoFuncionarioService service;

    /**
     * Creates a new instance of ManterTipoFuncionarioMBean
     */
    public ManterTipoFuncionarioMBean() {
    }

    @PostConstruct
    public void init() {
        this.tipoFuncionario = (TipoFuncionario) facesProducer.getFlash("tipoFuncionario");
        if (this.tipoFuncionario == null) {
            this.tipoFuncionario = new TipoFuncionario();
        }
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
    public String salvar() {
        try {
            this.service.salvarTipoFuncionario(tipoFuncionario);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Tipo de Funcionário! Erro: " + ex.getMessage());
            Logger.getLogger(ManterTipoFuncionarioMBean.class.getName()).log(Level.SEVERE, null, ex);
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
            this.service.deletarTipoFuncionario(tipoFuncionario);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Tipo Funcionário. Erro: " + ex.getMessage());
            Logger.getLogger(ManterTipoFuncionarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-TIPOFUNCIONARIO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-TIPOFUNCIONARIO-DELETAR");
    }

    /**
     * #############################################################################################
     */
    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public TipoFuncionarioService getService() {
        return service;
    }

    public void setService(TipoFuncionarioService service) {
        this.service = service;
    }

}
