package testetivia.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import testetivia.com.model.UsuarioModel;
import testetivia.com.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;	
	
	@Transactional
	public UsuarioModel save(UsuarioModel usuario) {
		return repository.save(usuario);
	}

	public Optional<UsuarioModel> findById(Long id) {
		return repository.findById(id);
	}

	public List<UsuarioModel> findByLoginContaining(String nome){
		return repository.findByLoginContaining(nome);
	}
	
	public Page<UsuarioModel> pageAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<UsuarioModel> pages(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}
	
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}	

	public Boolean existsByLogin(String login) {
		return repository.existsByLogin(login);
	}	

	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
