package br.com.eddydata.minhacidade.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eddydata.minhacidade.entity.Pessoa;
import br.com.eddydata.minhacidade.entity.Usuario;

@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
