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
@Table
@Entity
public class EntradaEstoqueVacina implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idEntradaEstoque;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEstoqueVacina", nullable = false, updatable = false)
    private EstoqueVacina estoqueVacina;

    @Temporal(TemporalType.DATE)
    private Date dataEntrada;

    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataCadastro;

    @Column(length = 20)
    private String numeroNotaFiscal;

    private Integer quantidade;

    private Double valorTotal;

    private Double valorUnitario;

    private Double valorDesconto;

    @Column(length = 100)
    private String observacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idFuncionario", updatable = false, nullable = false)
    private Funcionario responsavel;

    public EntradaEstoqueVacina() {
        estoqueVacina = new EstoqueVacina();
        responsavel = new Funcionario();
    }

    public Integer getIdEntradaEstoque() {
        return idEntradaEstoque;
    }

    public void setIdEntradaEstoque(Integer idEntradaEstoque) {
        this.idEntradaEstoque = idEntradaEstoque;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String Observacao) {
        this.observacao = Observacao;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public EstoqueVacina getEstoqueVacina() {
        return estoqueVacina;
    }

    public void setEstoqueVacina(EstoqueVacina estoqueVacina) {
        this.estoqueVacina = estoqueVacina;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.idEntradaEstoque);
        hash = 43 * hash + Objects.hashCode(this.estoqueVacina);
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
        final EntradaEstoqueVacina other = (EntradaEstoqueVacina) obj;
        if (!Objects.equals(this.idEntradaEstoque, other.idEntradaEstoque)) {
            return false;
        }
        if (!Objects.equals(this.estoqueVacina, other.estoqueVacina)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data Entrada " + dataEntrada + " - Quantidade " + quantidade;
    }

}
