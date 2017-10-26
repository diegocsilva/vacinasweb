/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.BairroService;
import br.com.imunita.vacinasweb.controller.service.CidadeService;
import br.com.imunita.vacinasweb.model.entity.Bairro;
import br.com.imunita.vacinasweb.model.entity.Cidade;
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
public class ManterBairroMBean extends GenericFormController implements Serializable {

    private Bairro bairro;
    private Cidade cidade;

    private List<Cidade> listaCidades;

    @Inject
    private BairroService service;

    @Inject
    private CidadeService cidadeService;

    /**
     * Creates a new instance of ManterBairroMBean
     */
    public ManterBairroMBean() {
    }

    @PostConstruct
    public void init() {

        this.bairro = (Bairro) facesProducer.getFlash("bairro");
        if (bairro == null) {
            bairro = new Bairro();
        } else {
            cidade = bairro.getCidade();
        }

        listaCidades = new ArrayList<>();
        listaCidades.addAll(cidadeService.getListaTodasCidades());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterBairro";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarBairro";
    }

    @Override
    public String salvar() {
        try {
            this.service.salvarBairro(bairro);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Bairro! Erro: " + ex.getMessage());
            Logger.getLogger(ManterBairroMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        this.bairro = new Bairro();
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            service.deletarBairro(bairro);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Bairro. Erro: " + ex.getMessage());
            Logger.getLogger(ManterBairroMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-BAIRRO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        if (bairro == null || bairro.getIdBairro() == null) {
            return false;
        }
        return seguranca.hasPermission("MANTER-BAIRRO-DELETAR");
    }

    /**
     * ###########################################################################
     */
    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public BairroService getService() {
        return service;
    }

    public void setService(BairroService service) {
        this.service = service;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
