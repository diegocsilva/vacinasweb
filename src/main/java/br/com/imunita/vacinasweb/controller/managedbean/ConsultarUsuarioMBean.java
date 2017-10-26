/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.controller.service.PerfilService;
import br.com.imunita.vacinasweb.controller.service.PermissaoService;
import br.com.imunita.vacinasweb.controller.service.UsuarioService;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import br.com.imunita.vacinasweb.model.wrapper.UsuarioWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class ConsultarUsuarioMBean extends GenericListController implements Serializable {

    private UsuarioWrapper usuario;
    private List<Usuario> listaUsuarios;

    //Filtros
    private Usuario usuarioSelecionado;
    private List<Funcionario> listaFuncionarios;
    private List<Perfil> listaPerfis;

    @Inject
    private UsuarioService service;

    @Inject
    private FuncionarioService funcionarioService;

    @Inject
    private PerfilService perfilService;

    /**
     * Creates a new instance of ConsultarUsuarioMBean
     */
    public ConsultarUsuarioMBean() {
    }

    @PostConstruct
    public void init() {

        usuario = new UsuarioWrapper();
        usuarioSelecionado = new Usuario();

        listaFuncionarios = new ArrayList<>();
        listaPerfis = new ArrayList<>();
        listaUsuarios = new ArrayList<>();

        listaFuncionarios.addAll(funcionarioService.getListaTodosFuncionarios());
        listaPerfis.addAll(perfilService.getListaTodosPerfils());
        listaUsuarios.addAll(service.getListaTodosUsuarios());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterUsuario";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarUsuario";
    }

    @Override
    public void filtrar() {
        this.listaUsuarios = this.service.getListaUsuarios(usuario);
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "usuario");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarUsuario((Usuario) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Usuário. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarUsuarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String deletarVarios() {
        return getNomeTelaConsultar();
    }

    @Override
    public void visualizar(Object objeto) {
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("CONSULTAR-USUARIO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-USUARIO-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-USUARIO-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-USUARIO-DELETAR");
    }

    /**
     * #########################################################################
     */
    public UsuarioWrapper getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioWrapper usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public UsuarioService getService() {
        return service;
    }

    public void setService(UsuarioService service) {
        this.service = service;
    }

    public FuncionarioService getFuncionarioService() {
        return funcionarioService;
    }

    public void setFuncionarioService(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public PerfilService getPerfilService() {
        return perfilService;
    }

    public void setPerfilService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public List<Perfil> getListaPerfis() {
        return listaPerfis;
    }

    public void setListaPerfis(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

}
