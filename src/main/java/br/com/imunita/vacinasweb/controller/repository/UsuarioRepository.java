/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import br.com.imunita.vacinasweb.model.wrapper.UsuarioWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class UsuarioRepository extends GenericRepository {

    public List<Usuario> getListaUsuariosFake(Integer qtdeRegistros, Integer qtdeSobrenomes) {
        List<Usuario> listaRetorno = new ArrayList<>();
        Usuario usuario;
        Funcionario funcionario;
        for (int qtd = 0; qtd < qtdeRegistros; qtd++) {
            usuario = new Usuario();
            funcionario = new Funcionario();
            usuario.setIdUsuario(qtd);
            usuario.setLogin(GeradorRandom.gerarNome(0));
            usuario.setAdministrador(GeradorRandom.gerarBoolean());
            usuario.setAtivo(GeradorRandom.gerarBoolean());

            funcionario.setNome(GeradorRandom.gerarNome(qtdeSobrenomes));
            funcionario.setAtivo(GeradorRandom.gerarBoolean());

            usuario.setFuncionario(funcionario);

            listaRetorno.add(usuario);
        }

        return listaRetorno;
    }

    public List<Usuario> getListaTodosUsuarios() {
        return findAll(Usuario.class);
    }

    public Usuario salvarUsuario(Usuario usuario) throws Exception {

        if (usuario.getIdUsuario() != null) {
            return this.atualizarUsuario(usuario);
        } else {
            return this.adicionarUsuario(usuario);
        }
    }

    public Usuario getUsuarioById(Integer idUsuario) throws Exception {
        return getEntity(Usuario.class, idUsuario);
    }

    public void deletarUsuario(Usuario usuario) throws Exception {
        removeEntity(usuario);
    }

    private Usuario adicionarUsuario(Usuario usuario) throws Exception {
        return addEntity(usuario);
    }

    private Usuario atualizarUsuario(Usuario usuario) throws Exception {
        return setEntity(usuario);
    }

    public Usuario getUsuarioByLogin(String userLogin) {
        Usuario usuario = null;

        try {
            usuario = (Usuario) this.getEntityManager().createQuery("SELECT usuario FROM Usuario AS usuario WHERE usuario.login = :userLogin AND usuario.ativo = :usuarioAtivo ", Usuario.class)
                    .setParameter("userLogin", userLogin)
                    .setParameter("usuarioAtivo", Boolean.TRUE)
                    .getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return usuario;

    }

    public List<Usuario> getListaUsuarios(UsuarioWrapper wrapper) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        HashMap<String, Object> filtros = new HashMap<>();

        StringBuilder sql = new StringBuilder(" SELECT u FROM Usuario AS u ");
        sql.append(" LEFT JOIN u.listaPerfis per ");

        if (wrapper instanceof UsuarioWrapper) {

            sql.append(" WHERE u.idUsuario IS NOT NULL ");
            if (wrapper.getLogin() != null && !wrapper.getLogin().isEmpty()) {
                sql.append(" AND u.login LIKE '%");
                sql.append(wrapper.getLogin());
                sql.append("%' ");
            }

            if (wrapper.getFuncionario() instanceof Funcionario && wrapper.getFuncionario().getId() != null) {
                sql.append(" AND u.funcionario = :funcionario ");
                filtros.put("funcionario", wrapper.getFuncionario());
            }

            if (wrapper.getPerfil() instanceof Perfil && wrapper.getPerfil().getIdPerfil() != null) {
                sql.append(" AND per = :perfil ");
                filtros.put("perfil", wrapper.getPerfil());
            }

            if (wrapper.getSituacao() != null) {
                sql.append("  AND u.ativo = :ativo");
                filtros.put("ativo", wrapper.getSituacao());
            }
        }

        Query query = this.getEntityManager().createQuery(sql.toString(), Usuario.class);

        for (Map.Entry<String, Object> filtro : filtros.entrySet()) {
            String key = filtro.getKey();
            Object value = filtro.getValue();
            query.setParameter(key, value);
        }

        try {
            listaUsuarios = (List<Usuario>) query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }

        return listaUsuarios;
    }
}
