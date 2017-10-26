/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Bairro;
import br.com.imunita.vacinasweb.model.entity.Cidade;
import br.com.imunita.vacinasweb.model.entity.Endereco;
import br.com.imunita.vacinasweb.model.entity.Estado;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class FuncionarioRepository extends GenericRepository {

    public List<Funcionario> getListaTodosFuncionarios() {
        return findAll(Funcionario.class);
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) throws Exception {

        funcionario.setEndereco(validarEndereco(funcionario.getEndereco()));

        if (funcionario.getId() != null) {
            return this.atualizarFuncionario(funcionario);
        } else {
            return this.adicionarFuncionario(funcionario);
        }
    }

    private Funcionario adicionarFuncionario(Funcionario funcionario) throws Exception {
        return addEntity(funcionario);
    }

    private Funcionario atualizarFuncionario(Funcionario funcionario) throws Exception {
        return setEntity(funcionario);
    }

    public Funcionario getFuncionarioById(Integer idFuncionario) throws Exception {
        return getEntity(Funcionario.class, idFuncionario);
    }

    public void deletarFuncionario(Funcionario funcionario) throws Exception {
        removeEntity(funcionario);
    }

    private Endereco validarEndereco(Endereco endereco) {
        if (endereco instanceof Endereco) {
            if (endereco.getBairro() instanceof Bairro && endereco.getBairro().getIdBairro() != null) {
                if (endereco.getBairro().getCidade() instanceof Cidade
                        && endereco.getBairro().getCidade().getIdCidade() != null) {
                    if (endereco.getBairro().getCidade().getEstado() instanceof Estado
                            && endereco.getBairro().getCidade().getEstado().getIdEstado() != null) {
                        return endereco;
                    } else {
                        endereco.getBairro().getCidade().setEstado(null);
                    }
                } else {
                    endereco.getBairro().setCidade(null);
                }
            } else {
                endereco.setBairro(null);
            }
            return endereco;
        } else {
            return null;
        }
    }

    public List<Funcionario> getListaFuncionarios(Funcionario funcionario) throws Exception {

        StringBuilder sql = new StringBuilder(" SELECT func FROM Funcionario AS func WHERE func.id IS NOT NULL ");
        HashMap<String, Object> filtros = new HashMap<>();

        if (funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            sql.append(" AND func.nome LIKE '%");
            sql.append(funcionario.getNome());
            sql.append("%' ");
        }

        if (funcionario.getTipoFuncionario() != null) {
            sql.append(" AND func.tipoFuncionario = :tipoFuncionario ");
            filtros.put("tipoFuncionario", funcionario.getTipoFuncionario());
        }

        if (funcionario.getAtivo() != null) {
            sql.append(" AND func.ativo = :ativo ");
            filtros.put("ativo", funcionario.getAtivo());
        }

//        Query query = getEntityManager().createQuery(" SELECT func FROM Funcionario AS func WHERE func.id IS NOT NULL  ");
        Query query = getEntityManager().createQuery(sql.toString());
        for (Map.Entry<String, Object> filtro : filtros.entrySet()) {
            String key = filtro.getKey();
            Object value = filtro.getValue();

            query.setParameter(key, value);
        }

        return query.getResultList();
    }

}
