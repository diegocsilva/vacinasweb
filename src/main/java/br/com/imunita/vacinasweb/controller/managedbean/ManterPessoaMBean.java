/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.PessoaService;
import br.com.imunita.vacinasweb.model.entity.Pessoa;
import br.com.imunita.vacinasweb.model.enuns.EnumSexo;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author rodolpho.sotolani
 */
@ManagedBean
@ViewScoped
public class ManterPessoaMBean extends GenericFormController implements Serializable {

    private Pessoa pessoa;

    private List<EnumSexo> listaSexo;

    @Inject
    private PessoaService service;

    /**
     * Creates a new instance of ManterPessoaMBean
     */
    public ManterPessoaMBean() {
    }

    @PostConstruct
    public void init() {
        listaSexo = Arrays.asList(EnumSexo.values());
        pessoa = new Pessoa();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public String salvar() {
        try {
            this.service.salvar(this.pessoa);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Pessoa! Erro: " + ex.getMessage());
            Logger.getLogger(ManterPessoaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return true;
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return true;
    }

    @Override
    public String deletar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterPessoa";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarPessoa";
    }

    /**
     * *
     * ############################################################################
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<EnumSexo> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<EnumSexo> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public PessoaService getService() {
        return service;
    }

    public void setService(PessoaService service) {
        this.service = service;
    }

}
