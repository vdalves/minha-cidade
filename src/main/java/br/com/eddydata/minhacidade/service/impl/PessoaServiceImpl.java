package br.com.eddydata.minhacidade.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eddydata.minhacidade.entity.Pessoa;
import br.com.eddydata.minhacidade.repository.PessoaRepository;
import br.com.eddydata.minhacidade.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Override
	public Pessoa save(Pessoa o) {
		return repository.save(o);
	}

	@Override
	public Optional<Pessoa> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<Pessoa> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
