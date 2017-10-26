/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.service.EmpresaService;
import br.com.imunita.vacinasweb.controller.service.LoteVacinaService;
import br.com.imunita.vacinasweb.controller.service.VacinaService;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author rodolpho.sotolani
 */
@Named(value = "manterLoteVacinaMBean")
@ViewScoped
public class ManterLoteVacinaMBean extends GenericFormController implements Serializable {

    private LoteVacina loteVacina;

    private Vacina vacina;
    private Empresa fabricante;

    //FILTROS
    private List<Vacina> listaVacinas;
    private List<Empresa> listaFabricante;

    @Inject
    private LoteVacinaService service;

    @Inject
    private VacinaService vacinaService;

    @Inject
    private EmpresaService empresaService;

    /**
     * Creates a new instance of ManterLoteVacinaMBean
     */
    public ManterLoteVacinaMBean() {
    }

    @PostConstruct
    public void init() {

        this.loteVacina = (LoteVacina) facesProducer.getFlash("loteVacina");
        if (loteVacina == null) {
            loteVacina = new LoteVacina();
            vacina = new Vacina();
            fabricante = new Empresa();
        } else {
            fabricante = loteVacina.getFabricante();
            if (!(fabricante instanceof Empresa)) {
                fabricante = new Empresa();
            }
            vacina = loteVacina.getVacina();
            if (!(vacina instanceof Vacina)) {
                vacina = new Vacina();
            }
        }

        listaVacinas = new ArrayList<>();
        listaFabricante = new ArrayList<>();

        listaVacinas.addAll(this.vacinaService.getListaTodosVacinas());
        listaFabricante.addAll(this.empresaService.getListaTodasEmpresasFabricantes());
    }

    @Override
    public String salvar() {
        try {
            this.loteVacina.setVacina(this.vacina);
            this.loteVacina.setFabricante(this.fabricante);

            this.service.salvarLoteVacina(this.loteVacina);
            facesProducer.putFlash(this.loteVacina, "loteVacina");
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao Salvar Lote de Vacina! Erro: " + ex.getMessage());
            Logger.getLogger(ManterLoteVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaManter();
    }

    @Override
    public String cancelar() {
        return getNomeTelaConsultar();
    }

    @Override
    public String novo() {
        facesProducer.putFlash(new LoteVacina(), "loteVacina");
        return getNomeTelaManter();
    }

    @Override
    public String deletar() {
        try {
            this.service.deletarLoteVacina(this.loteVacina);
        } catch (Exception ex) {
            messages.addErrorMessage("Erro ao deletar o registro de Lote de Vacina. Erro: " + ex.getMessage());
            Logger.getLogger(ManterLoteVacinaMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getNomeTelaConsultar();
    }

    @Override
    public Boolean renderizarBotaoSalvar() {
        return true;
    }

    @Override
    public Boolean renderizarBotaoNovo() {
        return seguranca.hasPermission("MANTER-LOTEVACINA-NOVO");
    }

    @Override
    public Boolean renderizarBotaoDeletar() {
        return seguranca.hasPermission("MANTER-LOTEVACINA-DELETAR");
    }

    /**
     * ####################################################################### @return
     */
    @Override
    public String getNomeTelaManter() {
        return "ManterLoteVacina";
    }

    @Override
    public String getNomeTelaConsultar() {
        return "ConsultarLoteVacina";
    }

    public LoteVacina getLoteVacina() {
        return loteVacina;
    }

    public void setLoteVacina(LoteVacina loteVacina) {
        this.loteVacina = loteVacina;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public LoteVacinaService getService() {
        return service;
    }

    public void setService(LoteVacinaService service) {
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

    public Empresa getFabricante() {
        return fabricante;
    }

    public void setFabricante(Empresa fabricante) {
        this.fabricante = fabricante;
    }

    public List<Empresa> getListaFabricante() {
        return listaFabricante;
    }

    public void setListaFabricante(List<Empresa> listaFabricante) {
        this.listaFabricante = listaFabricante;
    }

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

}
