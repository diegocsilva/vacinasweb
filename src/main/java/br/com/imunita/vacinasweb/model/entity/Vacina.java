/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Rodolpho
 */
@Entity
@Table
public class Vacina implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idVacina;

    @ManyToMany(mappedBy = "listaVacinasFornecedor")
    private List<Empresa> listaFornecedor;

    @ManyToMany(mappedBy = "listaVacinasFabricante")
    private List<Empresa> listaFabricante;

    @Column(length = 100, nullable = false)
    private String nome;

    private Boolean usoAdulto;
    private Boolean usoInfantil;
    private Double precoVenda;
    private Integer quantidadeMinima;

    public Vacina() {
//        listaFornecedor = new ArrayList<>();
//        listaFabricante = new ArrayList<>();
    }

    public Integer getIdVacina() {
        return idVacina;
    }

    public void setIdVacina(Integer idVacina) {
        this.idVacina = idVacina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getUsoAdulto() {
        return usoAdulto;
    }

    public void setUsoAdulto(Boolean usoAdulto) {
        this.usoAdulto = usoAdulto;
    }

    public Boolean getUsoInfantil() {
        return usoInfantil;
    }

    public void setUsoInfantil(Boolean usoInfantil) {
        this.usoInfantil = usoInfantil;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public List<Empresa> getListaFornecedor() {
        if (listaFornecedor == null) {
            listaFornecedor = new ArrayList<>();
        }
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Empresa> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public List<Empresa> getListaFabricante() {
        if (listaFabricante == null) {
            listaFabricante = new ArrayList<>();
        }
        return listaFabricante;
    }

    public void setListaFabricante(List<Empresa> listaFabricante) {
        this.listaFabricante = listaFabricante;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idVacina);
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final Vacina other = (Vacina) obj;
        if (!Objects.equals(this.idVacina, other.idVacina)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

}
