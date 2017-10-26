/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.controller.service.TipoFuncionarioService;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.TipoFuncionario;
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
public class ConsultarFuncionarioMBean extends GenericListController implements Serializable {

    private Funcionario funcionario;
    private TipoFuncionario tipoFuncionario;
    private List<Funcionario> listaFuncionarios;
    private List<Funcionario> listaFuncionariosSelecionado;
    private List<TipoFuncionario> listaTiposFuncionarios;

    @Inject
    private FuncionarioService service;

    @Inject
    private TipoFuncionarioService tipoFuncionarioService;

    /**
     * Creates a new instance of FuncionarioMBean
     */
    public ConsultarFuncionarioMBean() {
    }

    @PostConstruct
    public void init() {
        funcionario = new Funcionario();
        tipoFuncionario = new TipoFuncionario();

        listaFuncionariosSelecionado = new ArrayList<>();
        listaFuncionarios = new ArrayList<>();
        listaTiposFuncionarios = new ArrayList<>();

        listaFuncionarios.addAll(this.service.getListaTodosFuncionarios());
        listaTiposFuncionarios.addAll(this.tipoFuncionarioService.getListaTodosTipoFuncionarios());

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
    public void filtrar() {
        try {
            if (tipoFuncionario instanceof TipoFuncionario && tipoFuncionario.getIdTipoFuncionario() != null) {
                this.funcionario.setTipoFuncionario(tipoFuncionario);
            } else {
                this.funcionario.setTipoFuncionario(null);
            }
            this.listaFuncionarios = this.service.getListaFuncionarios(funcionario);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao buscar registros de Funcionários. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarFuncionarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "funcionario");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarFuncionario((Funcionario) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Funcionário. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarFuncionarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaManter();
    }

    @Override
    public String deletarVarios() {
        for (Funcionario funcionarioSelecionado : listaFuncionariosSelecionado) {
            this.deletar(funcionarioSelecionado);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-FUNCIONARIO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-FUNCIONARIO-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-FUNCIONARIO-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-FUNCIONARIO-DELETAR");
    }

    /**
     * #############################################################
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public List<Funcionario> getListaFuncionariosSelecionado() {
        return listaFuncionariosSelecionado;
    }

    public void setListaFuncionariosSelecionado(List<Funcionario> listaFuncionariosSelecionado) {
        this.listaFuncionariosSelecionado = listaFuncionariosSelecionado;
    }

    public FuncionarioService getService() {
        return service;
    }

    public void setService(FuncionarioService service) {
        this.service = service;
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

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

}
