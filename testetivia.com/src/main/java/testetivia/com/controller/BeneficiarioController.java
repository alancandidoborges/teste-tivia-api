package testetivia.com.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import testetivia.com.helpers.BeneficiarioConvert;
import testetivia.com.dto.BeneficiarioDto;
import testetivia.com.enums.Message;
import testetivia.com.model.BeneficiarioModel;
import testetivia.com.service.BeneficiarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/beneficiario")
@Tag(name = "Tivia API: endpoint da tab_Beneficiario")
public class BeneficiarioController {
      @Autowired
	  private BeneficiarioService service;

	  @Operation(summary = "Inclui um novo registro", method = "POST")
	  @PostMapping("/post")
	  @Transactional
	  public ResponseEntity<Object> save(@RequestBody @Valid BeneficiarioDto beneficiario) {

	      if (service.existsByNome(beneficiario.getNome())){
	      	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.RECORD_ALREADY_EXISTS.toString());
		  }

	      var newBeneficiario = new BeneficiarioModel();
		  var conv = new BeneficiarioConvert();

		  newBeneficiario = conv.BeneficiarioDtoToModel(beneficiario);
		  newBeneficiario.setDataInclusao(LocalDateTime.now());
	      service.save(newBeneficiario);
	      return ResponseEntity.status(HttpStatus.CREATED).body(Message.RECORD_SUCCESS.toString());
	  }
	  @Operation(summary = "Rertorna uma lista de Beneficiarios com todos os registros", method = "GET")
	  @GetMapping("/findall")
	  public List<BeneficiarioDto> listaTodos(){
	      return service.listaTodos();
  	  }
	  @Operation(summary = "Busca de registro por código", method = "GET")
	  @GetMapping("/find-by-id/{id}")
	  public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id) {

	    Optional<BeneficiarioModel> optional = service.findById(id);
	    
	    if (!optional.isPresent())
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.RECORD_NOT_FOUND.toString());

	    return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	  }
	  @Operation(summary = "Listagem de registros por página (FULL)", method = "GET")
	  @GetMapping("/page-full")
	  public Page<BeneficiarioDto> getPages(Pageable pageable, @RequestParam String field, @RequestParam String direction) {
	    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Direction.valueOf(direction), field);
	    return BeneficiarioDto.converter(service.pageAll(pageable));
	  }
	  @Operation(summary = "Atualiza um registro existente", method = "PUT")
	  @PutMapping("/put/{id}")
	  public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
	                                       @RequestBody @Valid BeneficiarioDto beneficiario) {

		  if (!service.existsById(beneficiario.getId())){
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.RECORD_NOT_FOUND.toString());
		  }

		  var newBeneficiario = new BeneficiarioModel();
		  var conv = new BeneficiarioConvert();

		  newBeneficiario = conv.BeneficiarioDtoToModel(beneficiario);
		  newBeneficiario.setId(id);
		  newBeneficiario.setDataAtualizacao(LocalDateTime.now());

		  service.save(newBeneficiario);
		  return ResponseEntity.status(HttpStatus.OK).body(Message.RECORD_UPDATED.toString());
	  }

	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		  
	    if (!service.existsById(id))
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.RECORD_NOT_FOUND.toString());

	    service.delete(id);
	    return ResponseEntity.status(HttpStatus.OK).body(Message.RECORD_DELETED.toString());
	  }
	
}
