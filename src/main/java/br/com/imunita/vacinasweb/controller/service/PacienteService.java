/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.PacienteRepository;
import br.com.imunita.vacinasweb.controller.utils.jsf.FacesProducer;
import br.com.imunita.vacinasweb.model.entity.Paciente;
import br.com.imunita.vacinasweb.model.wrapper.PacienteWrapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class PacienteService {

    @Inject
    private PacienteRepository repository;

    public List<Paciente> getListaTodosPacientes() {
        return repository.getListaTodosPacientes();
    }

    public Paciente getPacienteById(Integer idPaciente) throws Exception {
        return repository.getPacienteById(idPaciente);
    }

    public Paciente salvarPaciente(Paciente paciente) throws Exception {
        return repository.salvarPaciente(paciente);
    }

    public void deletarPaciente(Paciente paciente) throws Exception {
        repository.deletarPaciente(paciente);
    }

    public List<Paciente> getListaPaciente(PacienteWrapper paciente) throws Exception {
        return repository.getListaPaciente(paciente);
    }
}
