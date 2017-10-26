/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Rodolpho
 */
@Entity
@Table
@PrimaryKeyJoinColumn(name = "idContato")
public class Contato extends Pessoa implements Serializable {

    @OneToOne(mappedBy = "contato", cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false, name = "idEmpresa")
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
