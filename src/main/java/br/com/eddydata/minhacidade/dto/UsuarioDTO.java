package br.com.eddydata.minhacidade.dto;

import br.com.eddydata.minhacidade.entity.Pessoa;

public class UsuarioDTO {
	private Long id;	
	private String usuario;	
	private String senha;	
	private Integer tipo;	
	private Pessoa pessoa;
	
	public UsuarioDTO() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", usuario=" + usuario + ", senha=" + senha + ", tipo=" + tipo + ", pessoa="
				+ pessoa + "]";
	}
	
	
}
