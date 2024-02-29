package testetivia.com.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import testetivia.com.model.UsuarioModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
	private Long   id;
	private String login;
	//private String password;
	public UsuarioDto(UsuarioModel usuario) {
		this.id       = usuario.getId();
		this.login    = usuario.getLogin();
		//this.password = usuario.getPassword();
	}
	
	public static List<UsuarioDto> converter(List<UsuarioModel> usuario) {
		return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

	public static Page<UsuarioDto> converter(Page<UsuarioModel> usuario) {
		return usuario.map(UsuarioDto::new);
	}	
}
