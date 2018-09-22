package br.com.eddydata.minhacidade.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eddydata.minhacidade.dto.UsuarioDTO;
import br.com.eddydata.minhacidade.entity.Usuario;
import br.com.eddydata.minhacidade.response.Response;
import br.com.eddydata.minhacidade.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioControl {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<Response<List<UsuarioDTO>>> get() {
		Response<List<UsuarioDTO>> response = new Response<List<UsuarioDTO>>();
		List<Usuario> list = new ArrayList<>();
		List<UsuarioDTO> dto = new ArrayList<>();

		list = this.service.findAll();

		list.forEach(l -> dto.add(this.entityToDto(l)));

		response.setData(dto);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<UsuarioDTO>> post(@Valid @RequestBody UsuarioDTO dto, BindingResult result) {
		Response<UsuarioDTO> response = new Response<UsuarioDTO>();
		UsuarioDTO d = new UsuarioDTO();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		d = this.entityToDto(this.service.save(this.dtoToEntity(dto)));

		response.setData(d);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	ResponseEntity<Response<UsuarioDTO>> put(@Valid @RequestBody UsuarioDTO dto, BindingResult result) {
		Response<UsuarioDTO> response = new Response<UsuarioDTO>();
		UsuarioDTO d = new UsuarioDTO();

		this.checkBeforeUpdate(dto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		d = this.entityToDto(this.service.save(this.dtoToEntity(dto)));

		response.setData(d);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();

		Optional<Usuario> o = this.service.findById(id);

		if (!o.isPresent()) {
			response.getErrors().add("Orgão inexistente!");
			return ResponseEntity.badRequest().body(response);
		}

		this.service.deleteById(id);
		return ResponseEntity.ok(new Response<String>());
	}

	private UsuarioDTO entityToDto(Usuario o) {
		UsuarioDTO dto = new UsuarioDTO();

		dto.setId(o.getId());
		dto.setPessoa(o.getPessoa());
		dto.setSenha(o.getSenha());
		dto.setTipo(o.getTipo());
		dto.setUsuario(o.getUsuario());

		return dto;
	}

	private Usuario dtoToEntity(UsuarioDTO dto) {
		Usuario o = new Usuario();

		o.setId(dto.getId());
		o.setPessoa(dto.getPessoa());
		o.setSenha(dto.getSenha());
		o.setTipo(dto.getTipo());
		o.setUsuario(dto.getUsuario());

		return o;
	}

	private void checkBeforeUpdate(UsuarioDTO dto, BindingResult result) {
		Optional<Usuario> o = this.service.findById(dto.getId());

		if (!o.isPresent()) {
			result.addError(new ObjectError("usaurio", "Usuario não encontrado!"));
		}
	}
}
