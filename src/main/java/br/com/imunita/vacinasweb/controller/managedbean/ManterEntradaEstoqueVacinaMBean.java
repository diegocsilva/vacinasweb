/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EstoqueVacinaService;
import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.model.entity.EntradaEstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.EstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
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
 * @author Rodolpho
 */
@Named(value = "manterEntradaEstoqueVacinaMBean")
@ViewScoped
public class ManterEntradaEstoqueVacinaMBean extends GenericFormController implements Serializable {

    private EntradaEstoqueVacina entradaEstoque;
    private EstoqueVacina estoqueVacina;

    private List<Funcionario> listaResponsaveis;

    @Inject
    private EstoqueVacinaService service;

    @Inject
    private FuncionarioService funcionarioService;

    /**
     * Creates a new instance of ManterEntradaEstoqueVacina
     */
    public ManterEntradaEstoqueVacinaMBean() {
    }

    @PostConstruct
    public void init() {
        this.estoqueVacina = (EstoqueVacina) facesProducer.getFlash("estoqueVacina");
        if (estoqueVacina == null) {
            this.estoqueVacina = new EstoqueVacina();
        }
        this.entradaEstoque = (EntradaEstoqueVacina) facesProducer.getFlash("entradaEstoqueVacina");
        if (entradaEstoque == null) {
            this.entradaEstoque = new EntradaEstoqueVacina();
        }
        Funcionario funcionario = seguranca.getFuncionarioLogado();
        if (funcionario instanceof Funcionario && funcionario.getId() != null) {
            this.entradaEstoque.setResponsavel(funcionario);
        }

        this.entradaEstoque.setEstoqueVacina(estoqueVacina);

        this.listaResponsaveis = new ArrayList<>();
        this.listaResponsaveis = this.funcionarioService.getListaTodosFuncionarios();
    }

    @Override
    public String salvar() {
        try {
            this.service.salvarEntradaEstoque(this.entradaEstoque);
        } catch (Exception e) {
            messages.addErrorMessage("Erro ao salvar no Banco de Dados. Erro: " + e.getMessage());
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        facesProducer.putFlash(new EntradaEstoqueVacina(), "entradaEstoqueVacina");
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarEntradaEstoque(this.entradaEstoque);
        } catch (Exception ex) {
            Logger.getLogger(ManterEntradaEstoqueVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return seguranca.hasPermission("MANTER-ENTRADAESTOQUEVACINA-SALVAR");
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-ENTRADAESTOQUEVACINA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-ENTRADAESTOQUEVACINA-DELETAR");
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterEntradaEstoqueVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        facesProducer.putFlash(this.estoqueVacina, "estoqueVacina");
        return "ManterEstoqueVacina";
    }

    public EntradaEstoqueVacina getEntradaEstoque() {
        return entradaEstoque;
    }

    public void setEntradaEstoque(EntradaEstoqueVacina entradaEstoque) {
        this.entradaEstoque = entradaEstoque;
    }

    public List<Funcionario> getListaResponsaveis() {
        return listaResponsaveis;
    }

    public void setListaResponsaveis(List<Funcionario> listaResponsaveis) {
        this.listaResponsaveis = listaResponsaveis;
    }

    public EstoqueVacina getEstoqueVacina() {
        return estoqueVacina;
    }

    public void setEstoqueVacina(EstoqueVacina estoqueVacina) {
        this.estoqueVacina = estoqueVacina;
    }

    public EstoqueVacinaService getService() {
        return service;
    }

    public void setService(EstoqueVacinaService service) {
        this.service = service;
    }

}
