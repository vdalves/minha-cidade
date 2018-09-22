package br.com.eddydata.minhacidade.service;

import java.util.List;
import java.util.Optional;

import br.com.eddydata.minhacidade.entity.Pessoa;

public interface PessoaService {
		
	Pessoa save(Pessoa o);
	
	Optional<Pessoa> findById(Long id);

	List<Pessoa> findAll();
	
	void deleteById(Long id);
}
