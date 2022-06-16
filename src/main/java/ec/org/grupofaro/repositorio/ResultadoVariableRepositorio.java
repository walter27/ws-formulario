package ec.org.grupofaro.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.org.grupofaro.modelo.ResultadoVariable;
import ec.org.grupofaro.modelo.Usuario;

@Repository
public interface ResultadoVariableRepositorio extends JpaRepository<ResultadoVariable, Long> {

	public List<ResultadoVariable> findByUsuario(Usuario usuario);

	Optional<ResultadoVariable> findByCodigoVariableAndUsuario(Long idVariable, Usuario usuario);

}
