/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.security;

import br.com.imunita.vacinasweb.controller.service.UsuarioService;
import br.com.imunita.vacinasweb.controller.utils.CDIServiceLocator;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * fornece os detalhes do usuario
 *
 * @author Rodolpho
 */
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {

//        Ainda nao estara no context, portanto nao tem como utilizar o inject
        UsuarioService service = CDIServiceLocator.getBean(UsuarioService.class);
        Usuario usuario = service.getUsuarioByLogin(userLogin);
        UsuarioSistema user = null;

        if (usuario != null) {
            user = new UsuarioSistema(usuario, getAutorizacoes(usuario));
        }

        return user;
    }

    private Collection<? extends GrantedAuthority> getAutorizacoes(Usuario usuario) {
        List<SimpleGrantedAuthority> autorizacoes = new ArrayList<>();

        if (usuario.getAdministrador()) {
            autorizacoes.add(new SimpleGrantedAuthority("ADMINISTRADOR"));
            return autorizacoes;
        }

        if (usuario.getFuncionario() != null) {
            autorizacoes.add(new SimpleGrantedAuthority("FUNCIONARIO"));
        }

//        if (usuario.getPaciente() != null) {
//            groups.add(new SimpleGrantedAuthority("PACIENTE"));
//        }
        if (!usuario.getListaPerfis().isEmpty()) {
            for (Perfil perfil : usuario.getListaPerfis()) {

                if (perfil.getListaPermissoes() != null && !perfil.getListaPermissoes().isEmpty()) {

                    for (Permissao permissao : perfil.getListaPermissoes()) {
                        autorizacoes.add(new SimpleGrantedAuthority(permissao.getNome()));
                    }
                }
            }

        }
        return autorizacoes;
    }

}
