package testetivia.com.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import testetivia.com.enums.UserRole;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_usuario")
public class UsuarioModel implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O campo [login] deve ser preenchido.")
	@Column(name = "login", nullable = false, length = 300)		
	private String login;
	
	@NotEmpty(message = "O campo [senha] deve ser preenchido.")
	@Column(name = "password", nullable = false, length = 100, unique = true)		
	private String password;
	
	public UsuarioModel(String login, String password, UserRole role) {
		this.login    = login;
		this.password = password;
	}	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
