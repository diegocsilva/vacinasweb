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
public class ConsultarEmpresaMBean extends GenericListController implements Serializable {

    private Empresa empresa;

    private List<Empresa> listaEmpresas;
    private List<Contato> listaContatos;

    private char[] fornecedor;
    private char[] fabricante;
    private char[] parceira;

    @Inject
    private EmpresaService service;

    @Inject
    private ContatoService contatoService;

    /**
     * Creates a new instance of ConsultarEmpresaMBean
     */
    public ConsultarEmpresaMBean() {
    }

    @PostConstruct
    public void init() {

        this.empresa = new Empresa();

        this.listaEmpresas = new ArrayList<>();
        this.listaContatos = new ArrayList<>();

        this.listaEmpresas.addAll(this.service.getListaTodasEmpresas());
        this.listaContatos.addAll(this.contatoService.getListaTodosContatos());
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
    public void filtrar() {
        this.listaEmpresas = this.service.getListaTodasEmpresas();
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "empresa");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarEmpresa((Empresa) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Vacinação. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarEmpresaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
        this.empresa = (Empresa) objeto;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-EMPRESA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-EMPRESA-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-EMPRESA-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-EMPRESA-DELETAR");
    }

    /**
     * ################################################################################# @return
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
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

    public char[] getFabricante() {
        return fabricante;
    }

    public void setFabricante(char[] fabricante) {
        this.fabricante = fabricante;
    }

    public char[] getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(char[] fornecedor) {
        this.fornecedor = fornecedor;
    }

    public char[] getParceira() {
        return parceira;
    }

    public void setParceira(char[] parceira) {
        this.parceira = parceira;
    }

}
