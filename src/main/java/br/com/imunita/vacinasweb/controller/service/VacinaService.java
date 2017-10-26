/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.VacinaRepository;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import br.com.imunita.vacinasweb.model.entity.Vacinacao;
import br.com.imunita.vacinasweb.model.wrapper.vacinaWrapper;
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
public class VacinaService {

    @Inject
    private VacinaRepository repository;

    public List<Vacina> getListaTodosVacinas() {
        return repository.getListaTodasVacinas();
    }

    public Vacina getVacinaById(Integer idVacina) {
        try {
            return repository.getVacinaById(idVacina);
        } catch (Exception ex) {
            Logger.getLogger(VacinaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vacina salvarVacina(Vacina vacina) throws Exception {
        return repository.salvarVacina(vacina);
    }

    public void deletarVacina(Vacina vacina) throws Exception {
        repository.deletarVacina(vacina);
    }

    public Vacina getVacinaByVacinacao(Vacinacao vacinacao) {
        return repository.getVacinaByVacinacao(vacinacao);
    }

    public List<Vacina> getListaVacinas(vacinaWrapper wrapper) {
        return repository.getListaVacinas(wrapper);
    }
}
