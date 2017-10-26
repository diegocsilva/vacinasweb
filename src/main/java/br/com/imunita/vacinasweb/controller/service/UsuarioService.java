/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.UsuarioRepository;
import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import br.com.imunita.vacinasweb.model.wrapper.UsuarioWrapper;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class UsuarioService {

    @Inject
    private UsuarioRepository repository;

    /**
     * Metodo deve retornar ate 100 registros aleatorios de Usuarios com nomes com ate 4 sobrenomes
     *
     * @return
     */
    public List<Usuario> getListaUsuariosFake() {
        return repository.getListaUsuariosFake(GeradorRandom.gerarNumero(100), GeradorRandom.gerarNumero(4));
    }

    public List<Usuario> getListaTodosUsuarios() {
        return repository.getListaTodosUsuarios();
    }

    public Usuario getUsuarioById(Integer idUsuario) throws Exception {
        return repository.getUsuarioById(idUsuario);
    }

    public Usuario salvarUsuario(Usuario usuario) throws Exception {
        return repository.salvarUsuario(usuario);
    }

    public void deletarUsuario(Usuario usuario) throws Exception {
        repository.deletarUsuario(usuario);
    }

    public Usuario getUsuarioByLogin(String userLogin) {
        return repository.getUsuarioByLogin(userLogin);
    }

    public List<Usuario> getListaUsuarios(UsuarioWrapper wrapper) {
        return repository.getListaUsuarios(wrapper);
    }
}
