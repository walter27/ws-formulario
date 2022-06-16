package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.Asignacion;
import ec.org.grupofaro.repositorio.AsignacionRepositorio;

@Service
public class AsignacionServiceImpl implements AsignacionService {

	@Autowired
	AsignacionRepositorio asignacionReposirrio;

	@Override
	public void guardar(Asignacion asignacion) {
		asignacionReposirrio.save(asignacion);
	}

	@Override
	public Optional<Asignacion> buscarPorId(Long id) {
		return asignacionReposirrio.findById(id);
	}

	@Override
	public List<Asignacion> listaAsignacion() {
		return asignacionReposirrio.findAll();
	}

	@Override
	public List<Asignacion> listarPorUsuario(Long idUsuario) {
		return asignacionReposirrio.findByCodigoUsuario(idUsuario);
	}

}
