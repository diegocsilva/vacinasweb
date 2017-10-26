/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rodolpho
 */
@Entity
@Table
public class Vacinacao implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idVacinacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idLoteVacina")
    private LoteVacina loteVacina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idFuncionario_aplicador")
    private Funcionario aplicador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraVacinacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataHoraRegistro;

    @ManyToOne
    @JoinColumn(name = "idFuncionario_solicitante")
    private Funcionario medicoSolicitante;

    public Vacinacao() {
        loteVacina = new LoteVacina();
        aplicador = new Funcionario();
        paciente = new Paciente();
        medicoSolicitante = new Funcionario();
        dataHoraRegistro = new Date();
        dataHoraVacinacao = new Date();
    }

    public Integer getIdVacinacao() {
        return idVacinacao;
    }

    public void setIdVacinacao(Integer idVacinacao) {
        this.idVacinacao = idVacinacao;
    }

    public LoteVacina getLoteVacina() {
        return loteVacina;
    }

    public void setLoteVacina(LoteVacina loteVacina) {
        this.loteVacina = loteVacina;
    }

    public Funcionario getAplicador() {
        return aplicador;
    }

    public void setAplicador(Funcionario aplicador) {
        this.aplicador = aplicador;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getDataHoraVacinacao() {
        return dataHoraVacinacao;
    }

    public void setDataHoraVacinacao(Date dataHoraVacinacao) {
        this.dataHoraVacinacao = dataHoraVacinacao;
    }

    public Funcionario getMedicoSolicitante() {
        return medicoSolicitante;
    }

    public void setMedicoSolicitante(Funcionario medicoSolicitante) {
        this.medicoSolicitante = medicoSolicitante;
    }

    public Date getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(Date dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idVacinacao);
        hash = 53 * hash + Objects.hashCode(this.loteVacina);
        hash = 53 * hash + Objects.hashCode(this.paciente);
        hash = 53 * hash + Objects.hashCode(this.dataHoraVacinacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vacinacao other = (Vacinacao) obj;
        if (!Objects.equals(this.idVacinacao, other.idVacinacao)) {
            return false;
        }
        if (!Objects.equals(this.loteVacina, other.loteVacina)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraVacinacao, other.dataHoraVacinacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vacina " + loteVacina.getVacina().getNome() + " - Paciente " + paciente + " - Data " + dataHoraVacinacao;
    }

}
