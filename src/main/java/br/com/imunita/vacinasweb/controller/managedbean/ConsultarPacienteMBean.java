/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.PacienteService;
import br.com.imunita.vacinasweb.model.entity.Paciente;
import br.com.imunita.vacinasweb.model.enuns.EnumSexo;
import br.com.imunita.vacinasweb.model.wrapper.PacienteWrapper;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ConsultarPacienteMBean extends GenericListController implements Serializable {

    private PacienteWrapper paciente;

    private List<Paciente> listaPacientes;
    private List<Paciente> listaPacientesSelecionados;
    private List<EnumSexo> listaSexos;

    @Inject
    private PacienteService service;

    /**
     * Creates a new instance of ConsultarPacienteMBean
     */
    public ConsultarPacienteMBean() {
    }

    @PostConstruct
    public void init() {
        paciente = new PacienteWrapper();
        listaPacientes = new ArrayList<>();
        listaPacientesSelecionados = new ArrayList<>();

        listaPacientes.addAll(service.getListaTodosPacientes());
        listaSexos = Arrays.asList(EnumSexo.values());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterPaciente";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarPaciente";
    }

    @Override
    public void filtrar() {
        try {
            this.listaPacientes = this.service.getListaPaciente(this.paciente);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao buscar registros de Pacientes. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarPacienteMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "paciente");
        return getNomeTelaManter();
    }

    @Override
    public String novo() {
        listaPacientes.addAll(service.getListaTodosPacientes());
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarPaciente((Paciente) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Paciente. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarPacienteMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        listaPacientes.addAll(service.getListaTodosPacientes());
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-PACIENTE-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-PACIENTE-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-PACIENTE-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-PACIENTE-DELETAR");
    }

    /**
     * #########################################################################
     */
    public PacienteWrapper getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteWrapper paciente) {
        this.paciente = paciente;
    }

    public PacienteService getService() {
        return service;
    }

    public void setService(PacienteService service) {
        this.service = service;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Paciente> getListaPacientesSelecionados() {
        return listaPacientesSelecionados;
    }

    public void setListaPacientesSelecionados(List<Paciente> listaPacientesSelecionados) {
        this.listaPacientesSelecionados = listaPacientesSelecionados;
    }

    public List<EnumSexo> getListaSexos() {
        return listaSexos;
    }

    public void setListaSexos(List<EnumSexo> listaSexos) {
        this.listaSexos = listaSexos;
    }

}
