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
import testetivia.com.helpers.DocumentoConvert;
import testetivia.com.dto.DocumentoDto;
import testetivia.com.enums.Message;
import testetivia.com.model.DocumentoModel;
import testetivia.com.service.DocumentoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/documento")
@Tag(name = "Tivia API: endpoint da tab_Beneficiario")
public class DocumentoController {
	  @Autowired
	  private DocumentoService service;

	  @Operation(summary = "Inclui um novo registro", method = "POST")
	  @Transactional
	  @PostMapping("/post")
	  public ResponseEntity<Object> save(@RequestBody @Valid DocumentoDto documento) {

	    if (service.existsByDescricao(documento.getDescricao())){
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.RECORD_ALREADY_EXISTS.toString());
	    }

		var conv         = new DocumentoConvert();
		var newDocumento = new DocumentoModel();
		newDocumento     = conv.DocumentoDtoToModel(documento);

	    newDocumento.setDataInclusao(LocalDateTime.now());

        service.save(newDocumento);
	    return ResponseEntity.status(HttpStatus.CREATED).body(newDocumento);
	  }
	  @Operation(summary = "Rertorna uma lista de Beneficiarios com todos os registros", method = "GET")
	  @GetMapping("/findall")
	  public List<DocumentoDto> listaTodos(){
  		  return service.listaTodos();
      }
	  @Operation(summary = "Rertorna uma lista de Beneficiarios com todos os registros", method = "GET")
	  @GetMapping("/beneficiario/{codigo}")
	  public List<DocumentoModel> findByCodigoBeneficiario(@PathVariable(value = "codigo") Long codigo){
	      return service.findByCodigoBeneficiario(codigo);
  	  }
	  @Operation(summary = "Busca de registro por código", method = "GET")
	  @GetMapping("/find-by-id/{id}")
	  public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id) {

	    Optional<DocumentoModel> optional = service.findById(id);
	    
	    if (!optional.isPresent())
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.RECORD_NOT_FOUND.toString());

	    return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	  }
	  @Operation(summary = "Listagem de registros por página (FULL)", method = "GET")
	  @GetMapping("/page-full")
	  public Page<DocumentoDto> getPageFull(Pageable pageable, @RequestParam String field, @RequestParam String direction) {
	    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Direction.valueOf(direction), field);
	    return DocumentoDto.converter(service.pageAll(pageable));
	  }
	  @Operation(summary = "Listagem de registros por página (FULL)", method = "GET")
	  @GetMapping("/find-by-descricao/{descricao}")
	  public List<DocumentoModel> getByDescription(@PathVariable(value = "descricao") String descricao) {
	    return service.findByDescricaoContaining(descricao);
	  }
	  @Operation(summary = "Atualiza um registro existente", method = "PUT")
	  @PutMapping("/put/{id}")
	  public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
	                                       @RequestBody @Valid DocumentoDto documento) {

		  if (!service.existsById(documento.getId())){
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.RECORD_NOT_FOUND.toString());
		  }

		  var conv         = new DocumentoConvert();
		  var newDocumento = new DocumentoModel();
		  newDocumento     = conv.DocumentoDtoToModel(documento);

		  newDocumento.setId(id);
		  newDocumento.setDataAtualizacao(LocalDateTime.now());

	      service.save(newDocumento);
	      return ResponseEntity.status(HttpStatus.OK).body(Message.RECORD_UPDATED.toString());
	  }
	  @Operation(summary = "Exclui um registro", method = "DELETE")
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		  
	    if (!service.existsById(id))
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.RECORD_NOT_FOUND.toString());

	    service.delete(id);
	    return ResponseEntity.status(HttpStatus.OK).body(Message.RECORD_DELETED.toString());
	  }

}
