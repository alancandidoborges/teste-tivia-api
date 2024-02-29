package testetivia.com.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class BeneficiarioDto {

	private Long id;

	@NotEmpty(message = "O campo [Nome] deve ser preenchido.")
	private String nome;

	private String telefone;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo [Data-Nascimento] deve ser preenchido.")
	private LocalDate dataNascimento;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataAtualizacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataInclusao;

	private List<DocumentoDto> documentos;

	public BeneficiarioDto(BeneficiarioModel beneficiario){
		super();
		this.id              = beneficiario.getId();
		this.nome            = beneficiario.getNome();
		this.telefone        = beneficiario.getTelefone();
		this.dataNascimento  = beneficiario.getDataNascimento();
		this.dataAtualizacao = beneficiario.getDataAtualizacao();
		this.dataInclusao    = beneficiario.getDataInclusao();

		if (beneficiario.getDocumentos().size() > 0)
			this.documentos  = DocumentoDto.converter(beneficiario.getDocumentos());
	}
	public static List<BeneficiarioDto> converter(List<BeneficiarioModel> beneficiarios) {
		return beneficiarios.stream().map(BeneficiarioDto::new).collect(Collectors.toList());
	}
	public static Page<BeneficiarioDto> converter(Page<BeneficiarioModel> beneficiarios) {
		return beneficiarios.map(BeneficiarioDto::new);
	}
}
	

