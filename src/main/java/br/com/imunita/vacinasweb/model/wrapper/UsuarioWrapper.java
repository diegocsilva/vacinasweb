/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.model.wrapper;

import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.Perfil;

/**
 *
 * @author Rodolpho
 */
public class UsuarioWrapper {

    private Funcionario funcionario;
    private Perfil perfil;
    private String login;
    private Boolean situacao;

    public UsuarioWrapper() {
        funcionario = new Funcionario();
        perfil = new Perfil();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }
}
