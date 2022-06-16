package ec.org.grupofaro.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.org.grupofaro.modelo.ResultadoVariante;
import ec.org.grupofaro.modelo.Usuario;

@Repository
public interface ResultadoVarianteRepositorio extends JpaRepository<ResultadoVariante, Long> {

	public List<ResultadoVariante> findByUsuario(Usuario usuario);
	
	public Optional<ResultadoVariante> findByCodigoVarianteAndUsuario(Long idVariante, Usuario usuario);

}
