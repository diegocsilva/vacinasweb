package br.com.imunita.vacinasweb.controller.security;

import br.com.imunita.vacinasweb.controller.utils.jsf.FacesProducer;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

    @Inject
    private FacesProducer facesProducer;

    public String getLoginUsuario() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioSistemaLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getLogin();
        }

        return nome;
    }

    private UsuarioSistema getUsuarioSistemaLogado() {
        UsuarioSistema usuario = null;

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) facesProducer.getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;
    }

    public boolean hasPermission(String nomePermissao) {

        if (getUsuarioSistemaLogado().getUsuario().getAdministrador()) {
            return true;
        }

        return facesProducer.getExternalContext().isUserInRole(nomePermissao);
    }

    /**
     * Retorna o Funcionario que esta vinculado ao usuario Logado no sistema. Caso nao tenha
     * funcionario vinculado, entao retornará null.
     *
     * @return Funcionar
     */
    public Funcionario getFuncionarioLogado() {
        Usuario usuario = getUsuarioLogado();
        if (usuario != null && usuario.getFuncionario() != null) {
            return usuario.getFuncionario();
        }
        return null;
    }

    /**
     * Retorna o Usuario logado no Sistema. Caso nao tenho usuario do Sistema (que mantem as
     * permissoes no cache) logado no sistema, entao retornará null;
     *
     * @return Usuario
     */
    public Usuario getUsuarioLogado() {
        UsuarioSistema usuario = getUsuarioSistemaLogado();
        if (usuario.getUsuario() != null) {
            return usuario.getUsuario();
        }
        return null;
    }
}
