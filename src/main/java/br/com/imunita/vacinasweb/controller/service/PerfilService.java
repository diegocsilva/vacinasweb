/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.PerfilRepository;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class PerfilService {

    @Inject
    private PerfilRepository repository;

    public List<Perfil> getListByFilters(Perfil perfil) {
        return repository.getListByFilters(perfil);
    }

    public List<Perfil> getListaTodosPerfils() {
        return repository.getListaTodosPerfils();
    }

    public Perfil salvarPerfil(Perfil perfil) throws Exception {
        return repository.salvarPerfil(perfil);
    }

    public Perfil getPerfilById(Integer idPerfil) throws Exception {
        return repository.getPerfilById(idPerfil);
    }

    public void deletarPerfil(Perfil perfil) throws Exception {
        this.repository.deletarPerfil(perfil);
    }

    public List<Perfil> getListaPerfisByUsuario(Usuario usuario) {
        return repository.getListaPerfisByUsuario(usuario);
    }
}
