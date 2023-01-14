package br.com.isidrocorp.Projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.isidrocorp.Projeto.model.Conta;

public interface ContaDao extends CrudRepository <Conta, Integer>{

}
