package testetivia.com.config.security.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CamposInvalidos {
	private String campo;
	private String erro;

	public CamposInvalidos() {
		super();
	}

	public CamposInvalidos(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
}
