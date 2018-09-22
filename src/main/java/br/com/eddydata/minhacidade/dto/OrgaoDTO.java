package br.com.eddydata.minhacidade.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrgaoDTO {

	private Long id;
	@NotNull(message = "Nome não pode ser nulo")
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
	@NotNull(message = "Código não pode ser nulo")
	@NotEmpty(message = "Código não pode ser vazio")
	private String codigo;

	public OrgaoDTO(){};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

