package testetivia.com.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import testetivia.com.model.DocumentoModel;

public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long>{
	@Query(value = "select * from tab_documento"
			+ " where beneficiario_id = :codigo"
			+ " order by beneficiario_id", nativeQuery = true)
	List<DocumentoModel> findByCodigoBeneficiario(@Param("codigo") Long codigo);
	
	Optional<DocumentoModel> findById(Long id);
	boolean existsByDescricao(String value);
	
	public List<DocumentoModel> findByDescricaoContaining(String value);
	
	boolean existsById(Long id);
}
