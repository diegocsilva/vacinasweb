/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.LoteVacinaRepository;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class LoteVacinaService {

    @Inject
    private LoteVacinaRepository repository;

    public List<LoteVacina> getListaTodosLotesVacina() {
        return repository.getListaTodosLotesVacina();
    }

    public List<LoteVacina> getListaLotesVacina(Vacina vacina) {
        return repository.getListaLotesVacina(vacina);
    }

    public LoteVacina salvarLoteVacina(LoteVacina loteVacina) throws Exception {
        return repository.salvar(loteVacina);
    }

    public void deletarLoteVacina(LoteVacina loteVacina) throws Exception {
        repository.deletar(loteVacina);
    }

}
