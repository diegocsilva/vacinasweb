/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.ContatoService;
import br.com.imunita.vacinasweb.controller.service.EmpresaService;
import br.com.imunita.vacinasweb.model.entity.Contato;
import br.com.imunita.vacinasweb.model.entity.Empresa;
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
public class ManterEmpresaMBean extends GenericFormController implements Serializable {

    private Empresa empresa;

    private Contato contato;

    private List<Contato> listaContatos;

    @Inject
    private EmpresaService service;

    @Inject
    private ContatoService contatoService;

    /**
     * Creates a new instance of ManterEmpresaMBean
     */
    public ManterEmpresaMBean() {
    }

    @PostConstruct
    public void init() {
        this.empresa = (Empresa) facesProducer.getFlash("empresa");
        this.contato = new Contato();
        if (this.empresa == null) {
            this.empresa = new Empresa();
        }
        this.listaContatos = new ArrayList<>();
        this.listaContatos = this.contatoService.getListaTodosContatos();
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterEmpresa";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarEmpresa";
    }

    @Override
    public String salvar() {
        try {
            if (this.contato.getId() != null) {
                this.empresa.setContato(this.contato);
            } else {
                this.empresa.setContato(null);
            }
            this.service.salvarEmpresa(this.empresa);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Empresa! Erro: " + ex.getMessage());
            Logger.getLogger(ManterEmpresaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        this.empresa = new Empresa();
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarEmpresa(this.empresa);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Empresa. Erro: " + ex.getMessage());
            Logger.getLogger(ManterEmpresaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-EMPRESA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-EMPRESA-DELETAR");
    }

    /**
     * ###########################################################################################
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EmpresaService getService() {
        return service;
    }

    public void setService(EmpresaService service) {
        this.service = service;
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public ContatoService getContatoService() {
        return contatoService;
    }

    public void setContatoService(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

}
