package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import ec.org.grupofaro.modelo.Asignacion;

public interface AsignacionService {

	public void guardar(Asignacion asignacion);

	public Optional<Asignacion> buscarPorId(Long id);

	List<Asignacion> listaAsignacion();

	public List<Asignacion> listarPorUsuario(Long idUsuario);

}
