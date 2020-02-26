package br.com.ciandt.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ciandt.desafio.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {


}
