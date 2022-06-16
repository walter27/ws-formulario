package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import ec.org.grupofaro.modelo.ResultadoVariante;
import ec.org.grupofaro.modelo.Usuario;

public interface ResultadoVarianteService {
	
	public void guardar(ResultadoVariante resultadoVariante);
	
	public List<ResultadoVariante> listar();
	
	public List<ResultadoVariante> listarPorUusario(Usuario usuario);
	
	public Optional<ResultadoVariante> buscarPorCodigoVarianteYPorUsuario(Long idVariante, Usuario usuario);

}
