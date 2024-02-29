package testetivia.com.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import testetivia.com.dto.BeneficiarioDto;
import testetivia.com.dto.DocumentoDto;
import testetivia.com.model.DocumentoModel;
import testetivia.com.repository.DocumentoRepository;

@Service
public class DocumentoService {
	@Autowired
	private DocumentoRepository repository;	
	
	@Transactional
	public DocumentoModel save(DocumentoModel Documento) {
		return repository.save(Documento);
	}

	public Optional<DocumentoModel> findById(Long id) {
		return repository.findById(id);
	}

	public List<DocumentoModel> findByDescricaoContaining(String descricao){
		return repository.findByDescricaoContaining(descricao);
	}
	public Page<DocumentoModel> pageAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<DocumentoModel> findByCodigoBeneficiario(Long codigo) {
	  return repository.findByCodigoBeneficiario(codigo);
	}

	public List<DocumentoDto> listaTodos(){
		return DocumentoDto.converter(repository.findAll());
	}
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}	

	public Boolean existsByDescricao(String descricao) {
		return repository.existsByDescricao(descricao);
	}	

	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
