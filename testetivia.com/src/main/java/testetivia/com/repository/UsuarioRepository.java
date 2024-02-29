package testetivia.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import testetivia.com.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	
	public UserDetails findByLogin(String login);
	
	boolean existsByLogin(String login);
	
	public List<UsuarioModel> findByLoginContaining(String login);
	
	@Query(value = "select * from tab_usuario where login = :login and password = :password", nativeQuery = true)
	public UsuarioModel login(String login, String password);	

}
