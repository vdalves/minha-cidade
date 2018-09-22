package br.com.eddydata.minhacidade.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eddydata.minhacidade.entity.Orgao;

@Transactional(readOnly = true)
public interface OrgaoRepository extends JpaRepository<Orgao, Long> {

	List<Orgao> findByNomeLike(String nome);

}
