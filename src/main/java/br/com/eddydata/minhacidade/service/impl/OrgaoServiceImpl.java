package br.com.eddydata.minhacidade.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eddydata.minhacidade.entity.Orgao;
import br.com.eddydata.minhacidade.repository.OrgaoRepository;
import br.com.eddydata.minhacidade.service.OrgaoService;

@Service
public class OrgaoServiceImpl implements OrgaoService {

	@Autowired
	private OrgaoRepository repository;

	@Override
	public List<Orgao> findByNomeLike(String nome) {
		return repository.findByNomeLike(nome);
	}

	@Override
	public Orgao save(Orgao o) {
		return repository.save(o);
	}

	@Override
	public Optional<Orgao> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<Orgao> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
