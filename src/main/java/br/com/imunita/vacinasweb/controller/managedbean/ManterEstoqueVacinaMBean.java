/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EmpresaService;
import br.com.imunita.vacinasweb.controller.service.EstoqueVacinaService;
import br.com.imunita.vacinasweb.controller.service.LoteVacinaService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.EstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author rodolpho.sotolani
 */
@Named(value = "manterEstoqueVacinaMBean")
@ViewScoped
public class ManterEstoqueVacinaMBean extends GenericFormController implements Serializable {

    private EstoqueVacina estoqueVacina;
    private Vacina vacina;
    private LoteVacina loteVacina;
    private Empresa fornecedor;

    private List<Vacina> listaVacinas;
    private List<LoteVacina> listaLoteVacinas;
    private List<Empresa> listaFornecedores;

    @Inject
    private EstoqueVacinaService service;

    @Inject
    private VacinaService vacinaService;

    @Inject
    private LoteVacinaService loteVacinaService;

    @Inject
    private EmpresaService empresaService;

    /**
     * Creates a new instance of ManterEstoqueVacinaMBean
     */
    public ManterEstoqueVacinaMBean() {
    }

    @PostConstruct
    public void init() {

        this.estoqueVacina = (EstoqueVacina) facesProducer.getFlash("estoqueVacina");
        if (estoqueVacina == null) {
            estoqueVacina = new EstoqueVacina();
        } else {
            loteVacina = estoqueVacina.getLoteVacina();
            if (!(loteVacina instanceof LoteVacina)) {
                loteVacina = new LoteVacina();
            }
            vacina = loteVacina.getVacina();
            if (!(vacina instanceof Vacina)) {
                vacina = new Vacina();
            }
            fornecedor = estoqueVacina.getFornecedor();
            if (!(fornecedor instanceof Empresa)) {
                fornecedor = new Empresa();
            }
        }
        this.listaVacinas = new ArrayList<>();
        this.listaLoteVacinas = new ArrayList<>();
        this.listaFornecedores = new ArrayList<>();

        this.listaLoteVacinas.addAll(this.loteVacinaService.getListaTodosLotesVacina());
        this.listaVacinas.addAll(this.vacinaService.getListaTodosVacinas());
        this.listaFornecedores.addAll(this.empresaService.getListaTodasEmpresasFornecedoras());
    }

    public void atualizarListaLotesVacina(ValueChangeEvent event) {
        Integer idVacina = (Integer) event.getNewValue();
        this.vacina = this.vacinaService.getVacinaById(idVacina);
        this.listaLoteVacinas = this.loteVacinaService.getListaLotesVacina(vacina);
    }

    @Override
    public String salvar() {
        try {
            this.service.salvar(this.estoqueVacina);
        } catch (Exception ex) {
            Logger.getLogger(ManterEstoqueVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        facesProducer.putFlash(new EstoqueVacina(), "estoqueVacina");
        return getNomeTelaManter();
    }

    public String novaEntrada() {
        facesProducer.putFlash(this.estoqueVacina, "estoqueVacina");
        return "ManterEntradaEstoqueVacina";
    }

    public String novaSaida() {
        facesProducer.putFlash(this.estoqueVacina, "estoqueVacina");
        return "ManterSaidaEstoqueVacina";
    }

    @Override
    public String deletar() {
        try {
            this.service.deletar(this.estoqueVacina);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Estoque de Vacina. Erro: " + ex.getMessage());
            Logger.getLogger(ManterEstoqueVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return this.seguranca.hasPermission("MANTER-ESTOQUEVACINA-NOVO");
    }

    public Boolean renderizarBotaoNovaEntrada() {
        if (this.estoqueVacina == null || this.estoqueVacina.getIdEstoqueVacina() == null) {
            return false;
        }
        return this.seguranca.hasPermission("MANTER-ESTOQUEVACINA-NOVAENTRADA");
    }

    public Boolean renderizarBotaoNovaSaida() {
        if (this.estoqueVacina == null || this.estoqueVacina.getIdEstoqueVacina() == null) {
            return false;
        }
        return this.seguranca.hasPermission("MANTER-ESTOQUEVACINA-NOVASAIDA");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return this.seguranca.hasPermission("MANTER-ESTOQUEVACINA-DELETAR");
    }

    @Override
    public String getNomeTelaManter() {
        return "ManterEstoqueVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarEstoqueVacina";
    }

    public EstoqueVacina getEstoqueVacina() {
        return estoqueVacina;
    }

    public void setEstoqueVacina(EstoqueVacina estoqueVacina) {
        this.estoqueVacina = estoqueVacina;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public EstoqueVacinaService getService() {
        return service;
    }

    public void setService(EstoqueVacinaService service) {
        this.service = service;
    }

    public List<Vacina> getListaVacinas() {
        return listaVacinas;
    }

    public void setListaVacinas(List<Vacina> listaVacinas) {
        this.listaVacinas = listaVacinas;
    }

    public VacinaService getVacinaService() {
        return vacinaService;
    }

    public void setVacinaService(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    public LoteVacina getLoteVacina() {
        return loteVacina;
    }

    public void setLoteVacina(LoteVacina loteVacina) {
        this.loteVacina = loteVacina;
    }

    public List<LoteVacina> getListaLoteVacinas() {
        return listaLoteVacinas;
    }

    public void setListaLoteVacinas(List<LoteVacina> listaLoteVacinas) {
        this.listaLoteVacinas = listaLoteVacinas;
    }

    public LoteVacinaService getLoteVacinaService() {
        return loteVacinaService;
    }

    public void setLoteVacinaService(LoteVacinaService loteVacinaService) {
        this.loteVacinaService = loteVacinaService;
    }

    public Empresa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Empresa fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Empresa> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Empresa> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

}
