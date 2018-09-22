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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eddydata.minhacidade.dto.OrgaoDTO;
import br.com.eddydata.minhacidade.entity.Orgao;
import br.com.eddydata.minhacidade.response.Response;
import br.com.eddydata.minhacidade.service.OrgaoService;

@RestController
@RequestMapping("api/orgao")
public class OrgaoControl {

	@Autowired
	private OrgaoService service;

	@GetMapping
	public ResponseEntity<Response<List<OrgaoDTO>>> get(@RequestParam(value = "nome", required = false) String nome) {
		Response<List<OrgaoDTO>> response = new Response<List<OrgaoDTO>>();
		List<Orgao> list = new ArrayList<>();
		List<OrgaoDTO> dto = new ArrayList<>();
		
		if (nome == null || nome.isEmpty()) {
			list = this.service.findAll();
		}else {
			list = this.service.findByNomeLike("%"+nome+"%");
		}

		list.forEach(l -> dto.add(this.entityToDto(l)));

		response.setData(dto);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<OrgaoDTO>> post(@Valid @RequestBody OrgaoDTO dto, BindingResult result) {
		Response<OrgaoDTO> response = new Response<OrgaoDTO>();
		OrgaoDTO d = new OrgaoDTO();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		d = this.entityToDto(this.service.save(this.dtoToEntity(dto)));

		response.setData(d);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	ResponseEntity<Response<OrgaoDTO>> put(@Valid @RequestBody OrgaoDTO dto, BindingResult result) {
		Response<OrgaoDTO> response = new Response<OrgaoDTO>();
		OrgaoDTO d = new OrgaoDTO();
		
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
		
		Optional<Orgao> o = this.service.findById(id);
		
		if (!o.isPresent()) {
			response.getErrors().add("Orgão inexistente!");
			return ResponseEntity.badRequest().body(response);
		}
		
		this.service.deleteById(id);
		return ResponseEntity.ok(new Response<String>());
	}

	private OrgaoDTO entityToDto(Orgao o) {
		OrgaoDTO dto = new OrgaoDTO();

		dto.setCodigo(o.getCodigo());
		dto.setNome(o.getNome());
		dto.setId(o.getId());

		return dto;
	}

	private Orgao dtoToEntity(OrgaoDTO dto) {
		Orgao o = new Orgao();

		o.setCodigo(dto.getCodigo());
		o.setNome(dto.getNome());
		o.setId(dto.getId());

		return o;
	}

	private void checkBeforeUpdate(OrgaoDTO dto, BindingResult result) {
		Optional<Orgao> o = this.service.findById(dto.getId());
		
		if (!o.isPresent()) {
			result.addError(new ObjectError("orgao", "Orgão não encontrado!"));
		}
	}
}
