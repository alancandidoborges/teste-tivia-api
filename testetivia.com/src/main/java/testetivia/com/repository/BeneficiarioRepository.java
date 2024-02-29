package testetivia.com.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import testetivia.com.model.BeneficiarioModel;

public interface BeneficiarioRepository extends JpaRepository<BeneficiarioModel, Long> {
	
	Optional<BeneficiarioModel> findById(Long id);
	boolean existsByNome(String value);

	boolean existsById(Long id);
}
