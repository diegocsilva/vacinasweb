/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rodolpho.sotolani
 */
@Entity
@Table
public class EstoqueVacina implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idEstoqueVacina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idLoteVacina", nullable = false, unique = true)
    private LoteVacina loteVacina;

    @ManyToOne
    @JoinColumn(name = "idFornecedor")
    private Empresa fornecedor;

    private Integer quantidadeEntrada;

    private Integer quantidadeSaida;

    private Integer quantidadeAtual;

    @OneToMany(mappedBy = "estoqueVacina")
    private List<EntradaEstoqueVacina> listaEntradasEstoque;

    @OneToMany(mappedBy = "estoqueVacina")
    private List<SaidaEstoqueVacina> ListaSaidasEstoque;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataCadastro;

    public EstoqueVacina() {
        loteVacina = new LoteVacina();
        fornecedor = new Empresa();
        quantidadeAtual = 0;
    }

    public Integer getIdEstoqueVacina() {
        return idEstoqueVacina;
    }

    public void setIdEstoqueVacina(Integer idEstoqueVacina) {
        this.idEstoqueVacina = idEstoqueVacina;
    }

    public LoteVacina getLoteVacina() {
        return loteVacina;
    }

    public void setLoteVacina(LoteVacina loteVacina) {
        this.loteVacina = loteVacina;
    }

    public Empresa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Empresa fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Integer quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public List<EntradaEstoqueVacina> getListaEntradasEstoque() {
        return listaEntradasEstoque;
    }

    public void setListaEntradasEstoque(List<EntradaEstoqueVacina> listaEntradasEstoque) {
        this.listaEntradasEstoque = listaEntradasEstoque;
    }

    public List<SaidaEstoqueVacina> getListaSaidasEstoque() {
        return ListaSaidasEstoque;
    }

    public void setListaSaidasEstoque(List<SaidaEstoqueVacina> ListaSaidasEstoque) {
        this.ListaSaidasEstoque = ListaSaidasEstoque;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Integer quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idEstoqueVacina);
        hash = 29 * hash + Objects.hashCode(this.loteVacina);
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
        final EstoqueVacina other = (EstoqueVacina) obj;
        if (!Objects.equals(this.idEstoqueVacina, other.idEstoqueVacina)) {
            return false;
        }
        if (!Objects.equals(this.loteVacina, other.loteVacina)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vacina " + loteVacina.getVacina() + " - Qtde Atual " + quantidadeAtual;
    }

}
