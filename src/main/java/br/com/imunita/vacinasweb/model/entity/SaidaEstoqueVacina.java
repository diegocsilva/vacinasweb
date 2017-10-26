/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.entity;

import br.com.imunita.vacinasweb.model.enuns.EnumMotivo;
import br.com.imunita.vacinasweb.model.enuns.EnumTipoSaida;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class SaidaEstoqueVacina implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idSaidaEstoque;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEstoqueVacina", nullable = false, updatable = false)
    private EstoqueVacina estoqueVacina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idFuncionario", nullable = false, updatable = false)
    private Funcionario responsavel;

    @Temporal(TemporalType.DATE)
    private Date dataSaida;

    private Integer quantidade;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EnumTipoSaida tipoSaida;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    private EnumMotivo motivo;

    private String observacao;

    public SaidaEstoqueVacina() {
        responsavel = new Funcionario();
        estoqueVacina = new EstoqueVacina();
    }

    public Integer getIdSaidaEstoque() {
        return idSaidaEstoque;
    }

    public void setIdSaidaEstoque(Integer idSaidaEstoque) {
        this.idSaidaEstoque = idSaidaEstoque;
    }

    public EstoqueVacina getEstoqueVacina() {
        return estoqueVacina;
    }

    public void setEstoqueVacina(EstoqueVacina estoqueVacina) {
        this.estoqueVacina = estoqueVacina;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public EnumTipoSaida getTipoSaida() {
        return tipoSaida;
    }

    public void setTipoSaida(EnumTipoSaida tipoSaida) {
        this.tipoSaida = tipoSaida;
    }

    public EnumMotivo getMotivo() {
        return motivo;
    }

    public void setMotivo(EnumMotivo motivo) {
        this.motivo = motivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.idSaidaEstoque);
        hash = 31 * hash + Objects.hashCode(this.estoqueVacina);
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
        final SaidaEstoqueVacina other = (SaidaEstoqueVacina) obj;
        if (!Objects.equals(this.idSaidaEstoque, other.idSaidaEstoque)) {
            return false;
        }
        if (!Objects.equals(this.estoqueVacina, other.estoqueVacina)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoSaida.getDescricao() + " - " + motivo.getDescricao();
    }

}
