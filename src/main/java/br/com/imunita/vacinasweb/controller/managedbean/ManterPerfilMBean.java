/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.PerfilService;
import br.com.imunita.vacinasweb.controller.service.PermissaoService;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class ManterPerfilMBean extends GenericFormController implements Serializable {

    private Perfil perfil;

    //PickList Permissao
    private DualListModel<Permissao> listaPermissaoPickList;
    private List<Permissao> listaPermissaoTarget;
    private List<Permissao> listaPermissaoSource;

    @Inject
    private PerfilService service;

    @Inject
    private PermissaoService permissaoService;

    /**
     * Creates a new instance of ManterPerfilMBean
     */
    public ManterPerfilMBean() {
    }

    @PostConstruct
    public void init() {
        this.perfil = (Perfil) facesProducer.getFlash("perfil");

        listaPermissaoSource = this.permissaoService.getListaTodasPermissoes();
        listaPermissaoTarget = new ArrayList<>();

        if (perfil == null) {
            perfil = new Perfil();
        } else {
            listaPermissaoTarget = this.permissaoService.getListaPermissoesByPerfil(perfil);
            listaPermissaoSource.removeAll(listaPermissaoTarget);
        }

        listaPermissaoPickList = new DualListModel<>(listaPermissaoSource, listaPermissaoTarget);
    }

    public void atualizarPickList() {

        //TODO: refatorar metodo
        listaPermissaoSource = this.permissaoService.getListaTodasPermissoes();
        listaPermissaoTarget = this.permissaoService.getListaPermissoesByPerfil(perfil);
        listaPermissaoSource.removeAll(listaPermissaoTarget);

        listaPermissaoPickList = new DualListModel<>(listaPermissaoSource, listaPermissaoTarget);

    }

    @Override
    public String getNomeTelaManter() {
        return "ManterPerfil";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarPerfil";
    }

    @Override
    public String salvar() {
        try {
            this.perfil.setListaPermissoes(listaPermissaoPickList.getTarget());
            this.perfil = service.salvarPerfil(this.perfil);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Perfil! Erro: " + ex.getMessage());
            Logger.getLogger(ManterPerfilMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        this.perfil = new Perfil();
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            service.deletarPerfil(this.perfil);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Perfil. Erro: " + ex.getMessage());
            Logger.getLogger(ManterPerfilMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-PERFIL-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-PERFIL-DELETAR");
    }

    /**
     * *
     * ################################################################
     */
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public PerfilService getService() {
        return service;
    }

    public void setService(PerfilService service) {
        this.service = service;
    }

    public PermissaoService getPermissaoService() {
        return permissaoService;
    }

    public void setPermissaoService(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    public DualListModel<Permissao> getListaPermissaoPickList() {
        return listaPermissaoPickList;
    }

    public void setListaPermissaoPickList(DualListModel<Permissao> listaPermissaoPickList) {
        this.listaPermissaoPickList = listaPermissaoPickList;
    }

    public List<Permissao> getListaPermissaoTarget() {
        return listaPermissaoTarget;
    }

    public void setListaPermissaoTarget(List<Permissao> listaPermissaoTarget) {
        this.listaPermissaoTarget = listaPermissaoTarget;
    }

}
