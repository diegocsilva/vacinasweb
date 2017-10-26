/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EmpresaService;
import br.com.imunita.vacinasweb.controller.service.EstoqueVacinaService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.EstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author rodolpho.sotolani
 */
@Named(value = "consultarEstoqueVacinaMBean")
@ViewScoped
public class ConsultarEstoqueVacinaMBean extends GenericListController implements Serializable {

    private EstoqueVacina estoqueVacina;
    private List<EstoqueVacina> listaEstoqueVacinas;
    private List<Vacina> listaVacinas;
    private List<Empresa> listaFornecedores;
    //FILTROS
    private Vacina vacina;
    private LoteVacina loteVacina;
    private Empresa fornecedor;

    @Inject
    private EstoqueVacinaService service;

    @Inject
    private VacinaService vacinaService;

    @Inject
    private EmpresaService empresaService;

    /**
     * Creates a new instance of ConsultarEstoqueVacinaMBean
     */
    public ConsultarEstoqueVacinaMBean() {
    }

    @PostConstruct
    public void init() {
        this.estoqueVacina = new EstoqueVacina();

        this.vacina = new Vacina();
        this.loteVacina = new LoteVacina();
        this.fornecedor = new Empresa();

        this.listaEstoqueVacinas = new ArrayList<>();
        this.listaVacinas = new ArrayList<>();
        this.listaFornecedores = new ArrayList<>();

        this.listaEstoqueVacinas.addAll(this.service.getListaTodosEstoqueVacinas());
        this.listaVacinas.addAll(this.vacinaService.getListaTodosVacinas());
        this.listaFornecedores.addAll(this.empresaService.getListaTodasEmpresasFornecedoras());
    }

    @Override
    public void filtrar() {
        this.listaEstoqueVacinas = this.service.getListaTodosEstoqueVacinas();
    }

    @Override
    public String novo() {
        facesProducer.putFlash(new EstoqueVacina(), "estoqueVacina");
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "estoqueVacina");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletar((EstoqueVacina) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Estoque de Vacina. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarEstoqueVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-ESTOQUEVACINA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-ESTOQUEVACINA-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-ESTOQUEVACINA-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-ESTOQUEVACINA-DELETAR");
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterEstoqueVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarEstoqueVacina";
    }

    /**
     * ############################################################################ @return
     */
    public EstoqueVacina getEstoqueVacina() {
        return estoqueVacina;
    }

    public void setEstoqueVacina(EstoqueVacina estoqueVacina) {
        this.estoqueVacina = estoqueVacina;
    }

    public List<EstoqueVacina> getListaEstoqueVacinas() {
        return listaEstoqueVacinas;
    }

    public void setListaEstoqueVacinas(List<EstoqueVacina> listaEstoqueVacinas) {
        this.listaEstoqueVacinas = listaEstoqueVacinas;
    }

    public EstoqueVacinaService getService() {
        return service;
    }

    public void setService(EstoqueVacinaService service) {
        this.service = service;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public List<Vacina> getListaVacinas() {
        return listaVacinas;
    }

    public void setListaVacinas(List<Vacina> listaVacinas) {
        this.listaVacinas = listaVacinas;
    }

    public LoteVacina getLoteVacina() {
        return loteVacina;
    }

    public void setLoteVacina(LoteVacina loteVacina) {
        this.loteVacina = loteVacina;
    }

    public Empresa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Empresa fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Empresa> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Empresa> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

}
