/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.BairroService;
import br.com.imunita.vacinasweb.controller.service.CidadeService;
import br.com.imunita.vacinasweb.controller.service.EstadoService;
import br.com.imunita.vacinasweb.model.entity.Bairro;
import br.com.imunita.vacinasweb.model.entity.Cidade;
import br.com.imunita.vacinasweb.model.entity.Estado;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.PostUpdate;

/**
 *
 * @author Rodolpho
 */
@Named(value = "fragmentEnderecoMBean")
@ConversationScoped
public class FragmentEnderecoMBean implements Serializable {

    private List<Bairro> listaBairros;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;

    @Inject
    private BairroService bairroService;

    @Inject
    private CidadeService cidadeService;

    @Inject
    private EstadoService estadoService;

    /**
     * Creates a new instance of FragmentEnderecoMBean
     */
    public FragmentEnderecoMBean() {
    }

    @PostConstruct
    @PostUpdate
    public void init() {
        listaBairros = new ArrayList<>();
        listaCidades = new ArrayList<>();
        listaEstados = new ArrayList<>();

        listaBairros.addAll(bairroService.getListaTodosBairros());
        listaCidades.addAll(cidadeService.getListaTodasCidades());
        listaEstados.addAll(estadoService.getListaTodosEstados());
    }

    public List<Bairro> getListaBairros() {
        return listaBairros;
    }

    public void setListaBairros(List<Bairro> listaBairros) {
        this.listaBairros = listaBairros;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public EstadoService getEstadoService() {
        return estadoService;
    }

    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

}
