/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.PermissaoRepository;
import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class PermissaoService {

    @Inject
    private PermissaoRepository repository;

    public List<Permissao> getListaPermissoesFake() {
        return repository.getListaPermissoesFake(GeradorRandom.gerarNumero(100));
    }

    public List<Permissao> getListByFilters(Permissao permissao) {
        return repository.getListByFilters(permissao);
    }

    public List<Permissao> getListaTodasPermissoes() {
        return repository.getListaTodasPermissoes();
    }

    public Permissao getPermissaoById(Integer idPermissao) throws Exception {
        return repository.getPermissaoById(idPermissao);
    }

    public void deletarPermissao(Permissao permissao) throws Exception {
        repository.deletarPermissao(permissao);
    }

    public Permissao salvarPermissao(Permissao permissao) throws Exception {
        return repository.salvarPermissao(permissao);
    }

    public List<Permissao> getListaPermissoesByPerfil(Perfil perfil) {
        return repository.getListaPermissoesByPerfil(perfil);
    }
}
