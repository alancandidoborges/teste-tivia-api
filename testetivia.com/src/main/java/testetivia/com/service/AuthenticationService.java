package testetivia.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import testetivia.com.model.UsuarioModel;
import testetivia.com.repository.UsuarioRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	final private UsuarioRepository repository;

	public AuthenticationService(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	public UserDetails findByLogin(String login) {
		return repository.findByLogin(login);
	}

	public UsuarioModel login(String login, String password) {
		return repository.login(login, password);
	}
	
	@Transactional
	public UsuarioModel save(UsuarioModel usuario) {
		return repository.save(usuario);
	}	
	
	public boolean existsByLogin(String login) {
		return repository.existsByLogin(login);
	}

}
