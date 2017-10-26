/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.PerfilService;
import br.com.imunita.vacinasweb.controller.service.PermissaoService;
import br.com.imunita.vacinasweb.controller.service.UsuarioService;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class ConsultarPerfilMBean extends GenericListController implements Serializable {

    private Perfil perfil;
    private Perfil perfilSelecionado;
    private char[] ativoInativo;
    private Permissao permissaoSelecionada;
    private Usuario usuarioSelecionado;

    private List<Perfil> listaPerfis;
    private List<Perfil> listaPerfisSelecionados;
    private List<Permissao> listaPermissao;
    private List<Permissao> listaPermissoesPerfilSelecionado;
    private List<Usuario> listaUsuario;

    @Inject
    private PerfilService service;

    @Inject
    private PermissaoService permissaoService;

    @Inject
    private UsuarioService usuarioService;

    /**
     * Creates a new instance of perfilMBean
     */
    public ConsultarPerfilMBean() {
    }

    @PostConstruct
    public void init() {
        perfil = new Perfil();
        perfilSelecionado = new Perfil();

        permissaoSelecionada = new Permissao();
        usuarioSelecionado = new Usuario();

        listaPermissao = new ArrayList<>();
        listaUsuario = new ArrayList<>();

        listaPerfis = new ArrayList<>();
        listaPerfisSelecionados = new ArrayList<>();
        listaPermissoesPerfilSelecionado = new ArrayList<>();

        listaPerfis.addAll(service.getListaTodosPerfils());
        listaPermissao.addAll(permissaoService.getListaTodasPermissoes());
        listaUsuario.addAll(usuarioService.getListaTodosUsuarios());
    }

    public void adicionarPerfilSelecionado(SelectEvent event) {
        listaPerfisSelecionados.add((Perfil) event.getObject());
    }

    public void removerPerfilSelecionado(SelectEvent event) {
        listaPerfisSelecionados.remove((Perfil) event.getObject());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterPerfil";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarPerfil";
    }

    @Override
    public void filtrar() {
        this.listaPerfis = this.service.getListByFilters(perfil);
    }

    public Permissao getPermissaoInListaPermissaoByIdPermissao(Integer idPermissao) {
        if (idPermissao == null) {
            return new Permissao();
        } else {
            for (Permissao permissao : this.listaPermissao) {
                if (permissao.getIdPermissao().equals(idPermissao)) {
                    return permissao;
                }
            }
        }
        return null;
    }

    public Usuario getUsuarioInListaUsuarioByIdUsuario(Integer idUsuario) {
        if (idUsuario == null) {
            return new Usuario();
        } else {
            for (Usuario usuario : this.listaUsuario) {
                if (usuario.getIdUsuario().equals(idUsuario)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "perfil");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarPerfil((Perfil) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Perfil. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarPerfilMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        for (Perfil perfilDeletado : listaPerfisSelecionados) {
            this.deletar(perfilDeletado);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {

    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-PERFIL-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-PERFIL-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-PERFIL-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-PERFIL-DELETAR");
    }

    /**
     * ###########################################################################################
     * @return
     */
    public List<Permissao> getListaPermissao() {
        return listaPermissao;
    }

    public void setListaPermissao(List<Permissao> listaPermissao) {
        this.listaPermissao = listaPermissao;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * ################################################################
     */
    public Permissao getPermissaoSelecionada() {
        return permissaoSelecionada;
    }

    public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
        this.permissaoSelecionada = permissaoSelecionada;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public PerfilService getService() {
        return service;
    }

    public void setService(PerfilService service) {
        this.service = service;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public char[] getAtivoInativo() {
        return ativoInativo;
    }

    public void setAtivoInativo(char[] ativoInativo) {
        this.ativoInativo = ativoInativo;
    }

    public List<Perfil> getListaPerfis() {
        return listaPerfis;
    }

    public void setListaPerfis(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

    public List<Perfil> getListaPerfisSelecionados() {
        return listaPerfisSelecionados;
    }

    public void setListaPerfisSelecionados(List<Perfil> listaPerfisSelecionados) {
        this.listaPerfisSelecionados = listaPerfisSelecionados;
    }

    public Perfil getPerfilSelecionado() {
        return perfilSelecionado;
    }

    public void setPerfilSelecionado(Perfil perfilSelecionado) {
        this.perfilSelecionado = perfilSelecionado;
    }

    public List<Permissao> getListaPermissoesPerfilSelecionado() {
        return listaPermissoesPerfilSelecionado;
    }

    public void setListaPermissoesPerfilSelecionado(List<Permissao> listaPermissoesPerfilSelecionado) {
        this.listaPermissoesPerfilSelecionado = listaPermissoesPerfilSelecionado;
    }

    public PermissaoService getPermissaoService() {
        return permissaoService;
    }

    public void setPermissaoService(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

}
