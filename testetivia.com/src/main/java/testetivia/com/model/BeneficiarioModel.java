package testetivia.com.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tab_beneficiario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O campo [Nome] deve ser preenchido.")
	@Column(name = "nome", nullable = false, length = 100, unique = true)		
	private String nome;
	
	@Column(name = "telefone", length = 15)
	private String telefone;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo [Data-Nascimento] deve ser preenchido.")
	@Column(name = "dataNascimento", nullable = false)		
	private LocalDate dataNascimento;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	@Column(name = "data_atualizacao")	
	private LocalDateTime dataAtualizacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	@Column(name = "data_inclusao")	
	private LocalDateTime dataInclusao;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "beneficiario")
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "beneficiario")
	private List<DocumentoModel> documentos;
}
