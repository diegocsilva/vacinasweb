/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.PermissaoService;
import br.com.imunita.vacinasweb.model.entity.Permissao;
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
public class ManterPermissaoMBean extends GenericFormController implements Serializable {

    private Permissao permissao;

    @Inject
    private PermissaoService service;

    /**
     * Creates a new instance of ManterPermissaoMBean
     */
    public ManterPermissaoMBean() {
    }

    @PostConstruct
    public void init() {
        this.permissao = (Permissao) facesProducer.getFlash("permissao");
        if (permissao == null) {
            permissao = new Permissao();
        }
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterPermissao";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarPermissoes";
    }

    @Override
    public String salvar() {
        try {
            this.service.salvarPermissao(permissao);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Permissão! Erro: " + ex.getMessage());
            Logger.getLogger(ManterPermissaoMBean.class.getName()).log(Level.SEVERE, null, ex);
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
            this.service.deletarPermissao(this.permissao);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Permissão. Erro: " + ex.getMessage());
            Logger.getLogger(ManterPermissaoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-PERMISSAO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-PERMISSAO-DELETAR");
    }

    /**
     * *
     * ########################################################################
     */
    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public PermissaoService getService() {
        return service;
    }

    public void setService(PermissaoService service) {
        this.service = service;
    }
}
