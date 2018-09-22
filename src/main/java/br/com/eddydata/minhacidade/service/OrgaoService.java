package br.com.eddydata.minhacidade.service;

import java.util.List;
import java.util.Optional;

import br.com.eddydata.minhacidade.entity.Orgao;

public interface OrgaoService {
	
	List<Orgao> findByNomeLike(String nome);
	
	Orgao save(Orgao o);
	
	Optional<Orgao> findById(Long id);

	List<Orgao> findAll();
	
	void deleteById(Long id);
}
