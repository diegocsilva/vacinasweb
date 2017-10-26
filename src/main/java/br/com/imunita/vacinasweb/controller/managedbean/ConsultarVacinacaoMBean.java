/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.controller.service.PacienteService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.controller.service.VacinacaoService;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.Paciente;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import br.com.imunita.vacinasweb.model.entity.Vacinacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class ConsultarVacinacaoMBean extends GenericListController implements Serializable {

    //Filtros
    private Vacinacao vacinacao;
    private Vacina vacina;
    private Date dataVacinacaoInicial;
    private Date dataVacinacaoFinal;
    private List<Vacinacao> listaVacinacao;
    private List<Vacina> listaVacinas;
    private List<Funcionario> listaFuncionarios;
    private List<Paciente> listaPacientes;

    @Inject
    private VacinacaoService service;

    @Inject
    private FuncionarioService funcionarioService;

    @Inject
    private PacienteService pacienteService;

    @Inject
    private VacinaService vacinaService;

    /**
     * Creates a new instance of ConsultarVacinacaoMBean
     */
    public ConsultarVacinacaoMBean() {
    }

    @PostConstruct
    public void init() {

        vacinacao = new Vacinacao();
        vacina = new Vacina();

        listaFuncionarios = new ArrayList<>();
        listaVacinas = new ArrayList<>();
        listaVacinacao = new ArrayList<>();
        listaPacientes = new ArrayList<>();

        this.listaVacinacao.addAll(this.service.getListaTodosVacinacaos());
        this.listaFuncionarios.addAll(this.funcionarioService.getListaTodosFuncionarios());
        this.listaVacinas.addAll(this.vacinaService.getListaTodosVacinas());
        this.listaPacientes.addAll(this.pacienteService.getListaTodosPacientes());
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterVacinacao";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarVacinacao";
    }

    @Override
    public void filtrar() {
        this.listaVacinacao = this.service.getListaTodosVacinacaos();
    }

    @Override
    public String novo() {
        return getNomeTelaManter();
    }

    @Override
    public String editar(Object objeto) {
        facesProducer.putFlash(objeto, "vacinacao");
        return getNomeTelaManter();
    }

    @Override
    public String deletar(Object objeto) {
        try {
            this.service.deletarVacinacao((Vacinacao) objeto);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Vacinação. Erro: " + ex.getMessage());
            Logger.getLogger(ConsultarVacinacaoMBean.class.getName()).log(Level.SEVERE, null, ex);
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
        return seguranca.hasPermission("CONSULTAR-VACINACAO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletarVarios() {
        return seguranca.hasPermission("CONSULTAR-VACINACAO-DELETAR");
    }

    @Override
    public Boolean renderizarBotaoVisualizar() {
        return false;
    }

    @Override
    public Boolean renderizarBotaoEditar() {
        return seguranca.hasPermission("CONSULTAR-VACINACAO-EDITAR");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("CONSULTAR-VACINACAO-DELETAR");
    }

    /**
     * #############################################################################
     */
    public Vacinacao getVacinacao() {
        return vacinacao;
    }

    public void setVacinacao(Vacinacao vacinacao) {
        this.vacinacao = vacinacao;
    }

    public List<Vacinacao> getListaVacinacao() {
        return listaVacinacao;
    }

    public void setListaVacinacao(List<Vacinacao> listaVacinacao) {
        this.listaVacinacao = listaVacinacao;
    }

    public List<Vacina> getListaVacinas() {
        return listaVacinas;
    }

    public void setListaVacinas(List<Vacina> listaVacinas) {
        this.listaVacinas = listaVacinas;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Date getDataVacinacaoInicial() {
        return dataVacinacaoInicial;
    }

    public void setDataVacinacaoInicial(Date dataVacinacaoInicial) {
        this.dataVacinacaoInicial = dataVacinacaoInicial;
    }

    public Date getDataVacinacaoFinal() {
        return dataVacinacaoFinal;
    }

    public void setDataVacinacaoFinal(Date dataVacinacaoFinal) {
        this.dataVacinacaoFinal = dataVacinacaoFinal;
    }

    public VacinacaoService getService() {
        return service;
    }

    public void setService(VacinacaoService service) {
        this.service = service;
    }

}
