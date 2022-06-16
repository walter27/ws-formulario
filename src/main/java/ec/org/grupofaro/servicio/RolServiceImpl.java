package ec.org.grupofaro.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.Rol;
import ec.org.grupofaro.repositorio.RolRepositorio;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	RolRepositorio rolRepositorio;

	@Override
	public Optional<Rol> buscarPorId(Long id) {
		return rolRepositorio.findById(id);
	}

}
