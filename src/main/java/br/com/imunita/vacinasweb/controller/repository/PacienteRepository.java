/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Paciente;
import br.com.imunita.vacinasweb.model.enuns.EnumSexo;
import br.com.imunita.vacinasweb.model.wrapper.PacienteWrapper;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class PacienteRepository extends GenericRepository {

    public List<Paciente> getListaTodosPacientes() {
        return findAll(Paciente.class);
    }

    public Paciente salvarPaciente(Paciente paciente) throws Exception {
        if (paciente.getId() != null) {
            return this.atualizarPaciente(paciente);
        } else {
            return this.adicionarPaciente(paciente);
        }
    }

    private Paciente adicionarPaciente(Paciente paciente) throws Exception {
        return addEntity(paciente);
    }

    private Paciente atualizarPaciente(Paciente paciente) throws Exception {
        return setEntity(paciente);
    }

    public Paciente getPacienteById(Integer idPaciente) throws Exception {
        return getEntity(Paciente.class, idPaciente);
    }

    public void deletarPaciente(Paciente paciente) throws Exception {
        removeEntity(paciente);
    }

    public List<Paciente> getListaPaciente(PacienteWrapper paciente) throws Exception {

        StringBuilder sql = new StringBuilder(" SELECT pac FROM Paciente AS pac WHERE pac.id IS NOT NULL ");
        HashMap<String, Object> filtros = new HashMap<>();

        if (paciente.getCpf() != null && !paciente.getCpf().isEmpty()) {
            sql.append(" AND pac.cpf LIKE '%");
            sql.append(paciente.getCpf());
            sql.append("%' ");
        }

        if (paciente.getDataNascimentoFinal() != null) {
            sql.append(" AND pac.dataNascimento <= :dataNascimentoFinal ");
            filtros.put("dataNascimentoFinal", paciente.getDataNascimentoFinal());
        }
        if (paciente.getDataNascimentoInicial() != null) {
            sql.append(" AND pac.dataNascimento >= :dataNascimentoInicial ");
            filtros.put("dataNascimentoInicial", paciente.getDataNascimentoInicial());
        }

        if (paciente.getNome() != null && !paciente.getNome().isEmpty()) {
            sql.append(" AND pac.nome LIKE '%");
            sql.append(paciente.getNome());
            sql.append("%' ");
        }
        if (paciente.getNomeMae() != null && !paciente.getNomeMae().isEmpty()) {
            sql.append(" AND pac.nomeMae LIKE '%");
            sql.append(paciente.getNomeMae());
            sql.append("%' ");
        }
        if (paciente.getNomeResponsavel() != null && !paciente.getNomeResponsavel().isEmpty()) {
            sql.append(" AND pac.nomeResponsavel LIKE '%");
            sql.append(paciente.getNomeResponsavel());
            sql.append("%' ");
        }

        if (paciente.getSexo().length == 1) {
            for (char sexo : paciente.getSexo()) {
                sql.append(" AND pac.sexo = :sexo ");
                if (String.valueOf(EnumSexo.FEMININO.getSigla()).equals(String.valueOf(sexo))) {
                    filtros.put("sexo", EnumSexo.FEMININO);
                } else {
                    filtros.put("sexo", EnumSexo.MASCULINO);
                }
            }
        }

        Query query = getEntityManager().createQuery(sql.toString());
        for (Map.Entry<String, Object> filtro : filtros.entrySet()) {
            String key = filtro.getKey();
            Object value = filtro.getValue();

            query.setParameter(key, value);
        }

        return query.getResultList();
    }

}
