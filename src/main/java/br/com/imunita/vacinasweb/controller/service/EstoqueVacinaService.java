/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.EstoqueVacinaRepository;
import br.com.imunita.vacinasweb.model.entity.EntradaEstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.EstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.SaidaEstoqueVacina;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author rodolpho.sotolani
 */
@RequestScoped
public class EstoqueVacinaService {

    @Inject
    private EstoqueVacinaRepository repository;

    public List<EstoqueVacina> getListaTodosEstoqueVacinas() {
        return repository.getListaTodosEstoqueVacinas();
    }

    public EstoqueVacina salvar(EstoqueVacina estoqueVacina) throws Exception {
        return repository.salvar(estoqueVacina);
    }

    public void deletar(EstoqueVacina estoqueVacina) throws Exception {
        repository.deletar(estoqueVacina);
    }

    public EntradaEstoqueVacina salvarEntradaEstoque(EntradaEstoqueVacina entradaEstoque) throws Exception {
        //Atualiza o objeto do estoque vacina
        EstoqueVacina estoqueVacina = entradaEstoque.getEstoqueVacina();
        estoqueVacina = repository.getEstoqueVacina(estoqueVacina.getIdEstoqueVacina());
        //Adiciona a quantidade de entrada ao estoque
        Integer qtdeAtual = estoqueVacina.getQuantidadeAtual() + entradaEstoque.getQuantidade();
        Integer qtdeEntrada = estoqueVacina.getQuantidadeEntrada() + entradaEstoque.getQuantidade();
        //Atualiza o registro do estoque de vacina
        estoqueVacina.setQuantidadeAtual(qtdeAtual);
        estoqueVacina.setQuantidadeEntrada(qtdeEntrada);
        entradaEstoque.setEstoqueVacina(repository.salvar(estoqueVacina));
        //Salva a entrada do Estoque;
        return repository.salvarEntradaEstoque(entradaEstoque);
    }

    public void deletarEntradaEstoque(EntradaEstoqueVacina entradaEstoque) throws Exception {
        //Atualiza o objeto do estoque vacina
        EstoqueVacina estoqueVacina = entradaEstoque.getEstoqueVacina();
        estoqueVacina = repository.getEstoqueVacina(estoqueVacina.getIdEstoqueVacina());
        //Adiciona a quantidade de entrada ao estoque
        Integer qtdeAtual = estoqueVacina.getQuantidadeAtual() - entradaEstoque.getQuantidade();
        Integer qtdeEntrada = estoqueVacina.getQuantidadeEntrada() - entradaEstoque.getQuantidade();
        //Atualiza o registro do estoque de vacina
        estoqueVacina.setQuantidadeAtual(qtdeAtual);
        estoqueVacina.setQuantidadeEntrada(qtdeEntrada);
        entradaEstoque.setEstoqueVacina(repository.salvar(estoqueVacina));
        repository.deletarEntradaEstoque(entradaEstoque);
    }

    public SaidaEstoqueVacina salvarSaidaEstoque(SaidaEstoqueVacina saidaEstoque) throws Exception {
        //Atualiza o objeto do estoque vacina
        EstoqueVacina estoqueVacina = saidaEstoque.getEstoqueVacina();
        estoqueVacina = repository.getEstoqueVacina(estoqueVacina.getIdEstoqueVacina());
        //Adiciona a quantidade de entrada ao estoque
        Integer qtdeAtual = estoqueVacina.getQuantidadeAtual() - saidaEstoque.getQuantidade();
        Integer qtdeSaida = estoqueVacina.getQuantidadeSaida() + saidaEstoque.getQuantidade();
        //Atualiza o registro do estoque de vacina
        estoqueVacina.setQuantidadeAtual(qtdeAtual);
        estoqueVacina.setQuantidadeSaida(qtdeSaida);
        saidaEstoque.setEstoqueVacina(repository.salvar(estoqueVacina));
        //Salva a entrada do Estoque;
        return repository.salvarSaidaEstoque(saidaEstoque);
    }

    public void deletarSaidaEstoque(SaidaEstoqueVacina saidaEstoque) throws Exception {
        //Atualiza o objeto do estoque vacina
        EstoqueVacina estoqueVacina = saidaEstoque.getEstoqueVacina();
        estoqueVacina = repository.getEstoqueVacina(estoqueVacina.getIdEstoqueVacina());
        //Adiciona a quantidade de entrada ao estoque
        Integer qtdeAtual = estoqueVacina.getQuantidadeAtual() + saidaEstoque.getQuantidade();
        Integer qtdeSaida = estoqueVacina.getQuantidadeSaida() - saidaEstoque.getQuantidade();
        //Atualiza o registro do estoque de vacina
        estoqueVacina.setQuantidadeAtual(qtdeAtual);
        estoqueVacina.setQuantidadeSaida(qtdeSaida);
        saidaEstoque.setEstoqueVacina(repository.salvar(estoqueVacina));
        repository.deletarSaidaEstoque(saidaEstoque);
    }

    public EstoqueVacina getEstoqueVacina(LoteVacina loteVacina) {
        return repository.getEstoqueVacina(loteVacina);
    }
}
