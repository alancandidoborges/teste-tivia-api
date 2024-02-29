package testetivia.com.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import testetivia.com.model.BeneficiarioModel;
import testetivia.com.model.DocumentoModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDto {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O campo [Tipo-Documento] deve ser preenchido.")
	private String tipoDocumento;

	@NotEmpty(message = "O campo [Descrição] deve ser preenchido.")
	private String descricao;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataAtualizacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataInclusao;

	@JsonIgnore
	private BeneficiarioDto beneficiario;

	public DocumentoDto(DocumentoModel documento){
		super();
		this.id              = documento.getId();
		this.tipoDocumento   = documento.getTipoDocumento();
		this.descricao       = documento.getDescricao();
	}
	public static List<DocumentoDto> converter(List<DocumentoModel> documentos) {
		return documentos.stream().map(DocumentoDto::new).collect(Collectors.toList());
	}

	public static Page<DocumentoDto> converter(Page<DocumentoModel> documentos) {
		return documentos.map(DocumentoDto::new);
	}
}
