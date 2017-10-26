/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.security.Seguranca;
import br.com.imunita.vacinasweb.controller.utils.GenericMessages;
import br.com.imunita.vacinasweb.controller.utils.jsf.FacesProducer;
import br.com.imunita.vacinasweb.model.entity.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rodolpho.sotolani
 */
@ManagedBean
@ViewScoped
public class ConsultarPessoaMBean extends GenericListController implements Serializable {

    private Pessoa pessoa;
    private Pessoa pessoaSelecionada;

    private List<Pessoa> listaPessoas;

    /**
     * Creates a new instance of ConsultarPessoaMBean
     */
    public ConsultarPessoaMBean() {
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public String getNomeTelaManter() {
        return "ManterPessoa";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarPessoa";
    }

    @Override
    public void filtrar() {
    }

    @Override
    public void visualizar(Object objeto) {
        this.pessoaSelecionada = (Pessoa) objeto;
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        return getNomeTelaManter();
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public String deletar(Object objeto) {
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return true;
    }

    /**
     * ###########################################################################################
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(Seguranca seguranca) {
        this.seguranca = seguranca;
    }

    public GenericMessages getMessages() {
        return messages;
    }

    public void setMessages(GenericMessages messages) {
        this.messages = messages;
    }

    public FacesProducer getFacesProducer() {
        return facesProducer;
    }

    public void setFacesProducer(FacesProducer facesProducer) {
        this.facesProducer = facesProducer;
    }

}
