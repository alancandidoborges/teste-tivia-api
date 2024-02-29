package testetivia.com.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import testetivia.com.dto.UsuarioDto;
import testetivia.com.enums.Message;
import testetivia.com.model.UsuarioModel;
import testetivia.com.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioController {
	
	  @Autowired
	  private UsuarioService service;

	  @Operation(summary = "Inclui um novo registro", method = "POST")
	  @PostMapping("/post")
	  public ResponseEntity<Object> save(@RequestBody @Valid UsuarioModel usuario) {

	    if (service.existsByLogin(usuario.getLogin())){
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.RECORD_ALREADY_EXISTS.toString());
	    }

	    UsuarioModel newUsuario = new UsuarioModel();
	    
	    usuario.setId(null);
		String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
		usuario.setPassword(encryptedPassword);
		newUsuario = service.save(usuario);

	    if (newUsuario == null)
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.RECORD_NOT_SAVED.toString());

	    return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Registrado com sucesso");
	  }
	  
	  @Operation(summary = "Busca de registro por código", method = "GET")
	  @GetMapping("/find-by-id/{id}")
	  public ResponseEntity<Object> getOne(@PathVariable(value = "codigo") Long id) {

	    Optional<UsuarioModel> optional = service.findById(id);
	    
	    if (!optional.isPresent())
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.RECORD_NOT_FOUND.toString());

	    return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	  }

	  @Operation(summary = "Listagem de registros por página (CONTENT)", method = "GET")
	  @GetMapping("/page-data")
	  public List<UsuarioDto> getPageContent(Pageable pageable, @RequestParam String field, @RequestParam String direction) {
	    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Direction.valueOf(direction), field);
	    return UsuarioDto.converter(service.pageAll(pageable).getContent());
	  }

	  @Operation(summary = "Listagem de registros por página (FULL)", method = "GET")
	  @GetMapping("/page-full")
	  public Page<UsuarioDto> getPageFull(Pageable pageable, @RequestParam String field, @RequestParam String direction) {
	    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Direction.valueOf(direction), field);
	    return UsuarioDto.converter(service.pageAll(pageable));
	  }


	  @Operation(summary = "Listagem de registros por página (FULL)", method = "GET")
	  @GetMapping("/find-by-login/{login}")
	  public List<UsuarioModel> getByLogin(@PathVariable(value = "login") String login) {
	    return service.findByLoginContaining(login);
	  }

	  @Operation(summary = "Atualiza um registro existente", method = "PUT")
	  @PutMapping("/put/{id}")
	  public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
	                                       @RequestBody @Valid UsuarioModel Usuario) {

	    Usuario.setId(id);

	    if (service.save(Usuario) == null)
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.RECORD_NOT_SAVED.toString());

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
