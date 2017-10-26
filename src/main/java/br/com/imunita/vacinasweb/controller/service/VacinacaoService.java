/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.VacinacaoRepository;
import br.com.imunita.vacinasweb.model.entity.EstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.SaidaEstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.Vacinacao;
import br.com.imunita.vacinasweb.model.enuns.EnumTipoSaida;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class VacinacaoService {

    @Inject
    private VacinacaoRepository repository;

    @Inject
    private EstoqueVacinaService estoqueVacinaService;

    public List<Vacinacao> getListaTodosVacinacaos() {
        return repository.getListaTodosVacinacaos();
    }

    public Vacinacao getVacinacaoById(Integer idVacinacao) throws Exception {
        return repository.getVacinacaoById(idVacinacao);
    }

    public Vacinacao salvarVacinacao(Vacinacao vacinacao) throws Exception {
        if (vacinacao.getIdVacinacao() != null) {
            EstoqueVacina estoqueVacina = estoqueVacinaService.getEstoqueVacina(vacinacao.getLoteVacina());

            SaidaEstoqueVacina saidaEstoque = new SaidaEstoqueVacina();
            saidaEstoque.setEstoqueVacina(estoqueVacina);
            saidaEstoque.setResponsavel(vacinacao.getAplicador());
            saidaEstoque.setDataSaida(vacinacao.getDataHoraVacinacao());
            saidaEstoque.setQuantidade(1);
            saidaEstoque.setTipoSaida(EnumTipoSaida.APLICACAO);
            saidaEstoque.setObservacao("Vacinação realizada no paciente " + vacinacao.getPaciente().getNome() + " realizada no dia " + vacinacao.getDataHoraVacinacao());

            estoqueVacinaService.salvarSaidaEstoque(saidaEstoque);
        }
        return repository.salvarVacinacao(vacinacao);
    }

    public void deletarVacinacao(Vacinacao vacinacao) throws Exception {
        repository.deletarVacinacao(vacinacao);
    }
}
