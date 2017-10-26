/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.FuncionarioService;
import br.com.imunita.vacinasweb.controller.service.LoteVacinaService;
import br.com.imunita.vacinasweb.controller.service.PacienteService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.controller.service.VacinacaoService;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.Paciente;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import br.com.imunita.vacinasweb.model.entity.Vacinacao;
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
 * @author rodolpho.sotolani
 */
@ManagedBean
@ViewScoped
public class ManterVacinacaoMBean extends GenericFormController implements Serializable {

    //Filtros
    private Vacinacao vacinacao;
    private Vacina vacina;
    private List<Paciente> listaPacientes;
    private List<Vacina> listaVacinas;
    private List<LoteVacina> listaLoteVacinas;
    private List<Funcionario> listaFuncionarios;

    @Inject
    private VacinacaoService service;

    @Inject
    private FuncionarioService funcionarioService;

    @Inject
    private LoteVacinaService loteVacinaService;

    @Inject
    private PacienteService pacienteService;

    @Inject
    private VacinaService vacinaService;

    /**
     * Creates a new instance of ManterVacinacaoMBean
     */
    public ManterVacinacaoMBean() {
    }

    @PostConstruct
    public void init() {
        this.vacinacao = (Vacinacao) facesProducer.getFlash("vacinacao");

        if (vacinacao == null) {
            this.vacinacao = new Vacinacao();
            this.vacina = new Vacina();
        } else {
            this.vacina = this.vacinaService.getVacinaByVacinacao(this.vacinacao);
        }

        this.listaFuncionarios = new ArrayList<>();
        this.listaLoteVacinas = new ArrayList<>();
        this.listaPacientes = new ArrayList<>();
        this.listaVacinas = new ArrayList<>();

        this.listaFuncionarios.addAll(this.funcionarioService.getListaTodosFuncionarios());
        this.listaPacientes.addAll(this.pacienteService.getListaTodosPacientes());
        this.listaVacinas.addAll(this.vacinaService.getListaTodosVacinas());
    }

    public void atualizarListaLotesVacina() {
        listaLoteVacinas.addAll(this.loteVacinaService.getListaLotesVacina(this.vacina));
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
    public String salvar() {
        try {
            this.service.salvarVacinacao(this.vacinacao);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Vacinação! Erro: " + ex.getMessage());
            Logger.getLogger(ManterVacinacaoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        return this.getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarVacinacao(this.vacinacao);
        } catch (Exception e) {
            messages.addErrorMessage("Erro ao deletar o registro de Vacinação. Erro: " + e.getMessage());
            Logger.getLogger(ManterVacinacaoMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-VACINACAO-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-VACINACAO-DELETAR");
    }

    /**
     * ###################################################################
     */
    public Vacinacao getVacinacao() {
        return vacinacao;
    }

    public void setVacinacao(Vacinacao vacinacao) {
        this.vacinacao = vacinacao;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Vacina> getListaVacinas() {
        return listaVacinas;
    }

    public void setListaVacinas(List<Vacina> listaVacinas) {
        this.listaVacinas = listaVacinas;
    }

    public List<LoteVacina> getListaLoteVacinas() {
        return listaLoteVacinas;
    }

    public void setListaLoteVacinas(List<LoteVacina> listaLoteVacinas) {
        this.listaLoteVacinas = listaLoteVacinas;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public VacinacaoService getService() {
        return service;
    }

    public void setService(VacinacaoService service) {
        this.service = service;
    }

}
