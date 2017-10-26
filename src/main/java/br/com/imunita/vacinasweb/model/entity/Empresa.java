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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idEmpresa;

    @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "idContato")
    private Contato contato;

    @ManyToMany
    private List<Vacina> listaVacinasFornecedor;

    @ManyToMany
    private List<Vacina> listaVacinasFabricante;

    @Column(length = 100, nullable = false)
    private String nomeFantasia;
    @Column(length = 100)
    private String razaoSocial;
    @Column(length = 20)
    private String CNPJ;
    @Column(length = 15)
    private String telefone;
    @Column(length = 50)
    private String email;

    private Boolean fornecedor;
    private Boolean fabricante;
    private Boolean parceira;

    public Empresa() {
        contato = new Contato();
        listaVacinasFabricante = new ArrayList<>();
        listaVacinasFornecedor = new ArrayList<>();
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Boolean getFabricante() {
        return fabricante;
    }

    public void setFabricante(Boolean fabricante) {
        this.fabricante = fabricante;
    }

    public Boolean getParceira() {
        return parceira;
    }

    public void setParceira(Boolean parceira) {
        this.parceira = parceira;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<Vacina> getListaVacinasFornecedor() {
        return listaVacinasFornecedor;
    }

    public void setListaVacinasFornecedor(List<Vacina> listaVacinasFornecedor) {
        this.listaVacinasFornecedor = listaVacinasFornecedor;
    }

    public List<Vacina> getListaVacinasFabricante() {
        return listaVacinasFabricante;
    }

    public void setListaVacinasFabricante(List<Vacina> listaVacinasFabricante) {
        this.listaVacinasFabricante = listaVacinasFabricante;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idEmpresa);
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
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.idEmpresa, other.idEmpresa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeFantasia + " - " + razaoSocial + " - " + CNPJ;
    }

}
