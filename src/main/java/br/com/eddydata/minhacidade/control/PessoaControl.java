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

import br.com.eddydata.minhacidade.dto.PessoaDTO;
import br.com.eddydata.minhacidade.entity.Pessoa;
import br.com.eddydata.minhacidade.response.Response;
import br.com.eddydata.minhacidade.service.PessoaService;

@RestController
@RequestMapping("api/pessoa")
public class PessoaControl {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<Response<List<PessoaDTO>>> get() {
		Response<List<PessoaDTO>> response = new Response<List<PessoaDTO>>();
		List<Pessoa> list = new ArrayList<>();
		List<PessoaDTO> dto = new ArrayList<>();

		list = this.service.findAll();		

		list.forEach(l -> dto.add(this.entityToDto(l)));

		response.setData(dto);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<PessoaDTO>> post(@Valid @RequestBody PessoaDTO dto, BindingResult result) {
		Response<PessoaDTO> response = new Response<PessoaDTO>();
		PessoaDTO d = new PessoaDTO();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		d = this.entityToDto(this.service.save(this.dtoToEntity(dto)));

		response.setData(d);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	ResponseEntity<Response<PessoaDTO>> put(@Valid @RequestBody PessoaDTO dto, BindingResult result) {
		Response<PessoaDTO> response = new Response<PessoaDTO>();
		PessoaDTO d = new PessoaDTO();

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

		Optional<Pessoa> o = this.service.findById(id);

		if (!o.isPresent()) {
			response.getErrors().add("Orgão inexistente!");
			return ResponseEntity.badRequest().body(response);
		}

		this.service.deleteById(id);
		return ResponseEntity.ok(new Response<String>());
	}

	private PessoaDTO entityToDto(Pessoa o) {
		PessoaDTO dto = new PessoaDTO();

		dto.setCpf(o.getCpf());
		dto.setDtNascimento(o.getDtNascimento());
		dto.setEmail(o.getEmail());
		dto.setId(o.getId());
		dto.setSobrenome(o.getSobrenome());
		dto.setNome(o.getNome());
		dto.setOrgao(o.getOrgao());

		return dto;
	}

	private Pessoa dtoToEntity(PessoaDTO dto) {
		Pessoa o = new Pessoa();
		o.setCpf(dto.getCpf());
		o.setDtNascimento(dto.getDtNascimento());
		o.setEmail(dto.getEmail());
		o.setId(dto.getId());
		o.setSobrenome(dto.getSobrenome());
		o.setNome(dto.getNome());
		o.setOrgao(dto.getOrgao());

		return o;
	}

	private void checkBeforeUpdate(PessoaDTO dto, BindingResult result) {
		Optional<Pessoa> o = this.service.findById(dto.getId());

		if (!o.isPresent()) {
			result.addError(new ObjectError("pessoa", "Pessoa não encontrada!"));
		}
	}
}
