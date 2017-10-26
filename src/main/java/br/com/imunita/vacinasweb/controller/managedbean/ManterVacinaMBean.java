/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EmpresaService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.Vacina;
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
public class ManterVacinaMBean extends GenericFormController implements Serializable {

    private Vacina vacina;

    private List<Empresa> listaEmpresas;
    private List<Empresa> listaFabricantes;
    private List<Empresa> listaFornecedores;

    private Boolean fornecedor;
    private Boolean fabricante;

    @Inject
    private VacinaService service;

    @Inject
    private EmpresaService empresaService;

    /**
     * Creates a new instance of ManterVacinaMBean
     */
    public ManterVacinaMBean() {
    }

    @PostConstruct
    public void init() {
        this.listaFabricantes = new ArrayList<>();
        this.listaFornecedores = new ArrayList<>();

        this.vacina = (Vacina) facesProducer.getFlash("vacina");
        if (this.vacina == null) {
            this.vacina = new Vacina();
        } else {
            this.listaFabricantes = this.empresaService.getListaEmpresasFabricantes(this.vacina);
            this.listaFornecedores = this.empresaService.getListaEmpresasFornecedoras(this.vacina);
        }
        this.listaEmpresas = new ArrayList<>();
        this.listaEmpresas = this.empresaService.getListaTodasEmpresasFabricantesFornecedoras();

    }

    public void atualizaFabricante(Empresa empresa) {
        if (this.listaFabricantes instanceof ArrayList) {
            this.listaFabricantes = new ArrayList<>();
        }

        if (this.listaFabricantes.contains(empresa)) {
            this.listaFabricantes.remove(empresa);
        } else {
            this.listaFabricantes.add(empresa);
        }
    }

    public void atualizaFornecedor(Empresa empresa) {
        if (this.vacina.getListaFornecedor() == null) {
            this.vacina.setListaFornecedor(new ArrayList<Empresa>());
        }
        if (this.vacina.getListaFornecedor().contains(empresa)) {
            this.vacina.getListaFornecedor().remove(empresa);
        } else {
            this.vacina.getListaFornecedor().add(empresa);
        }
    }

    public boolean validaEmpresaFabricante(Empresa empresa) {
        return empresa.getFabricante();
    }

    public boolean validaEmpresaFornecedor(Empresa empresa) {
        return empresa.getFornecedor();
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarVacina";
    }

    @Override
    public String salvar() {
        try {
            if (!this.listaFabricantes.isEmpty()) {
                this.vacina.setListaFabricante(listaFabricantes);
            }
            if (!this.listaFornecedores.isEmpty()) {
                this.vacina.setListaFornecedor(listaFornecedores);
            }
            this.service.salvarVacina(this.vacina);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Vacina! Erro: " + ex.getMessage());
            Logger.getLogger(ManterVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarVacina(this.vacina);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Vacina. Erro: " + ex.getMessage());
            Logger.getLogger(ManterVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-VACINA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-VACINA-DELETAR");
    }

    /**
     * #####################################################################################
     */
    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public VacinaService getService() {
        return service;
    }

    public void setService(VacinaService service) {
        this.service = service;
    }

    public Boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Boolean getFabricante() {
        return fabricante;
    }

    public void setFabricante(Boolean fabricante) {
        this.fabricante = fabricante;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

}
