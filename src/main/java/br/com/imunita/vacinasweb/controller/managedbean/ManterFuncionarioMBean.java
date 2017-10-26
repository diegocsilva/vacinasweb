/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.controller.service.TipoFuncionarioService;
import br.com.imunita.vacinasweb.model.entity.Endereco;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.TipoFuncionario;
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
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class ManterFuncionarioMBean extends GenericFormController implements Serializable {

    private Funcionario funcionario;
    private Endereco endereco;

    private List<TipoFuncionario> listaTiposFuncionarios;

    private final List<EnumSexo> listaSexos = Arrays.asList(EnumSexo.values());

    @Inject
    private FuncionarioService service;

    @Inject
    private TipoFuncionarioService tipoFuncionarioService;

    /**
     * Creates a new instance of ManterFuncionarioMBean
     */
    public ManterFuncionarioMBean() {
    }

    @PostConstruct
    public void init() {
        this.funcionario = (Funcionario) facesProducer.getFlash("funcionario");
        if (funcionario == null) {
            funcionario = new Funcionario();
            endereco = new Endereco();
        } else {
            endereco = this.funcionario.getEndereco();
            if (endereco == null) {
                endereco = new Endereco();
            }
        }
        listaTiposFuncionarios = this.tipoFuncionarioService.getListaTodosTipoFuncionarios();
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterFuncionario";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarFuncionario";
    }

    @Override
    public String salvar() {
        try {
            if (!endereco.getNome().isEmpty()) {
                this.funcionario.setEndereco(endereco);
            } else {
                this.funcionario.setEndereco(null);
            }
            this.service.salvarFuncionario(funcionario);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Funcionário! Erro: " + ex.getMessage());
            Logger.getLogger(ManterFuncionarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        this.funcionario = new Funcionario();
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarFuncionario(funcionario);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Funcionário. Erro: " + ex.getMessage());
            Logger.getLogger(ManterFuncionarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-FUNCIONARIO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        if (!(this.funcionario instanceof Funcionario) || this.funcionario.getId() == null) {
            return false;
        }
        return seguranca.hasPermission("MANTER-FUNCIONARIO-DELETAR");
    }

    /**
     * ####################################################################
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioService getService() {
        return service;
    }

    public void setService(FuncionarioService service) {
        this.service = service;
    }

    public List<EnumSexo> getListaSexos() {
        return listaSexos;
    }

    public List<TipoFuncionario> getListaTiposFuncionarios() {
        return listaTiposFuncionarios;
    }

    public void setListaTiposFuncionarios(List<TipoFuncionario> listaTiposFuncionarios) {
        this.listaTiposFuncionarios = listaTiposFuncionarios;
    }

    public TipoFuncionarioService getTipoFuncionarioService() {
        return tipoFuncionarioService;
    }

    public void setTipoFuncionarioService(TipoFuncionarioService tipoFuncionarioService) {
        this.tipoFuncionarioService = tipoFuncionarioService;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
