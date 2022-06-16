package ec.org.grupofaro.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.org.grupofaro.modelo.Asignacion;

@Repository
public interface AsignacionRepositorio extends JpaRepository<Asignacion, Long> {
	
	public List<Asignacion> findByCodigoUsuario(Long idUsuario);

}
