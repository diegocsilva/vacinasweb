/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EstoqueVacinaService;
import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.model.entity.EstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.SaidaEstoqueVacina;
import br.com.imunita.vacinasweb.model.enuns.EnumMotivo;
import br.com.imunita.vacinasweb.model.enuns.EnumTipoSaida;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@Named(value = "manterSaidaEstoqueVacina")
@ViewScoped
public class ManterSaidaEstoqueVacinaMBean extends GenericFormController implements Serializable {

    private SaidaEstoqueVacina saidaEstoque;

    private EstoqueVacina estoqueVacina;

    private List<EnumTipoSaida> listaTipoSaida;
    private List<EnumMotivo> listaMotivos;
    private List<Funcionario> listaResponsaveis;

    @Inject
    private EstoqueVacinaService service;

    @Inject
    private FuncionarioService funcionarioService;

    /**
     * Creates a new instance of ManterSaidaEstoqueVacina
     */
    public ManterSaidaEstoqueVacinaMBean() {
    }

    @PostConstruct
    public void init() {
        this.estoqueVacina = (EstoqueVacina) facesProducer.getFlash("estoqueVacina");
        if (estoqueVacina == null) {
            this.estoqueVacina = new EstoqueVacina();
        }
        this.saidaEstoque = (SaidaEstoqueVacina) facesProducer.getFlash("saidaEstoqueVacina");
        if (saidaEstoque == null) {
            this.saidaEstoque = new SaidaEstoqueVacina();
        }
        this.saidaEstoque.setEstoqueVacina(estoqueVacina);

        this.listaMotivos = Arrays.asList(EnumMotivo.values());
        this.listaTipoSaida = Arrays.asList(EnumTipoSaida.values());
        this.listaResponsaveis = new ArrayList<>();

        this.listaResponsaveis.addAll(this.funcionarioService.getListaTodosFuncionarios());
    }

    @Override
    public String salvar() {
        try {
            this.service.salvarSaidaEstoque(this.saidaEstoque);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Saída de Estoque! Erro: " + ex.getMessage());
            Logger.getLogger(ManterSaidaEstoqueVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        facesProducer.putFlash(this.estoqueVacina, "estoqueVacina");
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        facesProducer.putFlash(this.estoqueVacina, "estoqueVacina");
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        facesProducer.putFlash(new SaidaEstoqueVacina(), "saidaEstoqueVacina");
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarSaidaEstoque(this.saidaEstoque);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Deletar Saída de Estoque! Erro: " + ex.getMessage());
            Logger.getLogger(ManterSaidaEstoqueVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        facesProducer.putFlash(this.estoqueVacina, "estoqueVacina");
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return seguranca.hasPermission("MANTER-SAIDAESTOQUEVACINA-SALVAR");
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-SAIDAESTOQUEVACINA-SALVAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-SAIDAESTOQUEVACINA-SALVAR");
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterSaidaEstoqueVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ManterEstoqueVacina";
    }

    public SaidaEstoqueVacina getSaidaEstoque() {
        return saidaEstoque;
    }

    public void setSaidaEstoque(SaidaEstoqueVacina saidaEstoque) {
        this.saidaEstoque = saidaEstoque;
    }

    public List<EnumTipoSaida> getListaTipoSaida() {
        return listaTipoSaida;
    }

    public void setListaTipoSaida(List<EnumTipoSaida> listaTipoSaida) {
        this.listaTipoSaida = listaTipoSaida;
    }

    public List<EnumMotivo> getListaMotivos() {
        return listaMotivos;
    }

    public void setListaMotivos(List<EnumMotivo> listaMotivos) {
        this.listaMotivos = listaMotivos;
    }

    public List<Funcionario> getListaResponsaveis() {
        return listaResponsaveis;
    }

    public void setListaResponsaveis(List<Funcionario> listaResponsaveis) {
        this.listaResponsaveis = listaResponsaveis;
    }

}
