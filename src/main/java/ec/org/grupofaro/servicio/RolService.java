package ec.org.grupofaro.servicio;

import java.util.Optional;

import ec.org.grupofaro.modelo.Rol;

public interface RolService {

	Optional<Rol> buscarPorId(Long id);

}
