package testetivia.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import testetivia.com.dto.BeneficiarioDto;
import testetivia.com.model.BeneficiarioModel;
import testetivia.com.repository.BeneficiarioRepository;

@Service
public class BeneficiarioService {
	@Autowired
	private BeneficiarioRepository repository;	
	@Transactional
	public BeneficiarioModel save(BeneficiarioModel Beneficiario) {
		return repository.save(Beneficiario);
	}
	public Optional<BeneficiarioModel> findById(Long id) {
		return repository.findById(id);
	}
	public List<BeneficiarioDto> listaTodos(){
		return BeneficiarioDto.converter(repository.findAll());
	}
	public Page<BeneficiarioModel> pageAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	public Page<BeneficiarioModel> pages(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	public boolean existsByNome(String value){ return repository.existsByNome(value); }
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
