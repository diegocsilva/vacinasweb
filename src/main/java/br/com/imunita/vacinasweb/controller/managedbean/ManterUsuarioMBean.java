/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.controller.service.PerfilService;
import br.com.imunita.vacinasweb.controller.service.UsuarioService;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.Perfil;
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
import org.primefaces.model.DualListModel;

/**
 *
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class ManterUsuarioMBean extends GenericFormController implements Serializable {

    private Usuario usuario;
    private Funcionario funcionario;

    private DualListModel<Perfil> listaPerfisPickList;
    private List<Perfil> listaPerfilSource;
    private List<Perfil> listaPerfilTarget;

    private ArrayList<Funcionario> listaFuncionarios;

    @Inject
    private UsuarioService service;

    @Inject
    private PerfilService perfilService;

    @Inject
    private FuncionarioService funcionarioService;

    @PostConstruct
    public void init() {

        this.usuario = (Usuario) facesProducer.getFlash("usuario");
        funcionario = new Funcionario();

        listaPerfilTarget = new ArrayList<>();
        listaPerfilSource = new ArrayList<>();
        listaFuncionarios = new ArrayList<>();

        listaPerfilSource.addAll(this.perfilService.getListaTodosPerfils());
        listaFuncionarios.addAll(funcionarioService.getListaTodosFuncionarios());

        if (usuario == null) {
            usuario = new Usuario();
        } else {
            listaPerfilTarget.addAll(perfilService.getListaPerfisByUsuario(usuario));
            listaPerfilSource.removeAll(listaPerfilTarget);
        }

        listaPerfisPickList = new DualListModel<>(listaPerfilSource, listaPerfilTarget);
    }

    public void atualizarPickListPerfil() {

        this.listaPerfilSource = this.perfilService.getListaTodosPerfils();
        this.listaPerfilTarget = this.perfilService.getListaPerfisByUsuario(this.usuario);
        this.listaPerfilSource.removeAll(listaPerfilTarget);
    }

    /**
     * Creates a new instance of UsuarioMBean
     */
    public ManterUsuarioMBean() {
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
    public String salvar() {
        try {
            this.usuario.setListaPerfis(listaPerfisPickList.getTarget());
            if (this.funcionario.getId() != null) {
                this.usuario.setFuncionario(funcionario);
            } else {
                this.usuario.setFuncionario(null);
            }
            this.usuario = service.salvarUsuario(this.usuario);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Usuário! Erro: " + ex.getMessage());
            Logger.getLogger(ManterUsuarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        this.usuario = new Usuario();
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            service.deletarUsuario(this.usuario);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Usuário. Erro: " + ex.getMessage());
            Logger.getLogger(ManterUsuarioMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-USUARIO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-USUARIO-DELETAR");
    }

    /**
     * ##################################################################
     */
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioService getService() {
        return service;
    }

    public void setService(UsuarioService service) {
        this.service = service;
    }

    public DualListModel<Perfil> getListaPerfisPickList() {
        return listaPerfisPickList;
    }

    public void setListaPerfisPickList(DualListModel<Perfil> listaPerfisPickList) {
        this.listaPerfisPickList = listaPerfisPickList;
    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public PerfilService getPerfilService() {
        return perfilService;
    }

    public void setPerfilService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public FuncionarioService getFuncionarioService() {
        return funcionarioService;
    }

    public void setFuncionarioService(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Perfil> getListaPerfilSource() {
        return listaPerfilSource;
    }

    public void setListaPerfilSource(List<Perfil> listaPerfilSource) {
        this.listaPerfilSource = listaPerfilSource;
    }

    public List<Perfil> getListaPerfilTarget() {
        return listaPerfilTarget;
    }

    public void setListaPerfilTarget(List<Perfil> listaPerfilTarget) {
        this.listaPerfilTarget = listaPerfilTarget;
    }

}
