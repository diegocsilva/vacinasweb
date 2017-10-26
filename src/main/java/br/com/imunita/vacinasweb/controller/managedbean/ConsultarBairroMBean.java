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
public class ConsultarBairroMBean extends GenericListController implements Serializable {

    private Bairro bairro;
    private Bairro bairroSelecionado;
    private List<Bairro> listaBairros;
    private List<Cidade> listaCidades;

    @Inject
    private BairroService service;

    @Inject
    private CidadeService cidadeService;

    @Inject
    private ManterBairroMBean bairroMBean;

    /**
     * Creates a new instance of bairroMBean
     */
    public ConsultarBairroMBean() {
    }

    @PostConstruct
    public void init() {
        bairro = new Bairro();

        listaBairros = new ArrayList<>();
        listaCidades = new ArrayList<>();

        listaBairros.addAll(service.getListaTodosBairros());
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
    public void filtrar() {
        this.listaBairros = this.service.filtrarBairros(this.bairro);
    }

    public Cidade getCidadeInListaCidadeByIdCidade(Integer idCidade) {
        if (idCidade == null) {
            return new Cidade();
        } else {
            for (Cidade cidade : this.listaCidades) {
                if (cidade.getIdCidade().equals(idCidade)) {
                    return cidade;
                }
            }
        }
        return null;
    }

    @Override
    public String novo() {
        facesProducer.putFlash(new Bairro(), "bairro");
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "bairro");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarBairro((Bairro) objeto);
        } catch (Exception ex) {
            Logger.getLogger(ConsultarBairroMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
        this.bairroSelecionado = (Bairro) objeto;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-BAIRRO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-BAIRRO-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-BAIRRO-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-BAIRRO-DELETAR");
    }

    /**
     * ###########################################################
     */
    /**
     *
     * @return
     */
    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<Bairro> getListaBairros() {
        return listaBairros;
    }

    public void setListaBairros(List<Bairro> listaBairros) {
        this.listaBairros = listaBairros;

    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public BairroService getService() {
        return service;
    }

    public void setService(BairroService service) {
        this.service = service;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public ManterBairroMBean getBairroMBean() {
        return bairroMBean;
    }

    public void setBairroMBean(ManterBairroMBean bairroMBean) {
        this.bairroMBean = bairroMBean;
    }

    public Bairro getBairroSelecionado() {
        return bairroSelecionado;
    }

    public void setBairroSelecionado(Bairro bairroSelecionado) {
        this.bairroSelecionado = bairroSelecionado;
    }

}
