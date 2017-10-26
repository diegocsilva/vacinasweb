/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.PermissaoService;
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

/**
 *
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class ConsultarPermissaoMBean extends GenericListController implements Serializable {

    private Permissao permissao;
    private List<Permissao> listaPermissoes;

    @Inject
    private PermissaoService service;

    @PostConstruct
    public void init() {
        permissao = new Permissao();
        listaPermissoes = new ArrayList<>();
        listaPermissoes = this.service.getListaTodasPermissoes();
    }

    /**
     * Creates a new instance of permissaoMBean
     */
    public ConsultarPermissaoMBean() {
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
    public void filtrar() {
        this.listaPermissoes = service.getListByFilters(this.permissao);
    }

    @Override
    public String novo() {
//        messages.addFatalMessage("Este Recurso não esta disponível para esta tela!");
//        return "";
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "permissao");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarPermissao((Permissao) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Permissão. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarPermissaoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        messages.addFatalMessage("Este Recurso não esta disponível para esta tela!");
        return "";
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-PERMISSAO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-PERMISSAO-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-PERMISSAO-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-PERMISSAO-DELETAR");
    }

    /**
     * #######################################################################
     */
    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public List<Permissao> getListaPermissoes() {
        return listaPermissoes;
    }

    public void setListaPermissoes(List<Permissao> listaPermissoes) {
        this.listaPermissoes = listaPermissoes;
    }

}
