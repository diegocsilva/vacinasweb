/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.PacienteService;
import br.com.imunita.vacinasweb.model.entity.Paciente;
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
public class ManterPacienteMBean extends GenericFormController implements Serializable {

    private Paciente paciente;

    private List<EnumSexo> listaSexos;

    @Inject
    private PacienteService service;

    @PostConstruct
    public void init() {
        this.paciente = (Paciente) facesProducer.getFlash("paciente");
        if (paciente == null) {
            paciente = new Paciente();
        }
        listaSexos = Arrays.asList(EnumSexo.values());
    }

    /**
     * Creates a new instance of ManterPacienteMBean
     */
    public ManterPacienteMBean() {
    }

    @Override
    public String salvar() {
        try {
            this.service.salvarPaciente(this.paciente);
            return getNomeTelaConsultar();
        } catch (Exception e) {
            messages.addErrorMessage("Erro ao salvar registro! Erro: " + e.getMessage());
            return "";
        }
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
            service.deletarPaciente(paciente);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Paciente. Erro: " + ex.getMessage());
            Logger.getLogger(ManterPacienteMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-PACIENTE-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-PACIENTE-DELETAR");
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterPaciente";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarPaciente";
    }

    /**
     * #################################################################
     */
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<EnumSexo> getListaSexos() {
        return listaSexos;
    }

    public void setListaSexos(List<EnumSexo> listaSexos) {
        this.listaSexos = listaSexos;
    }

    public PacienteService getService() {
        return service;
    }

    public void setService(PacienteService service) {
        this.service = service;
    }

}
