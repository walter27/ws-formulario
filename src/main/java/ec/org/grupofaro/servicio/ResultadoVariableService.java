package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import ec.org.grupofaro.modelo.ResultadoVariable;
import ec.org.grupofaro.modelo.Usuario;

public interface ResultadoVariableService {

	public void guardar(ResultadoVariable resultadoVariable);

	public List<ResultadoVariable> buscarPorUusario(Usuario usuario);

	public Optional<ResultadoVariable> buscarPorCodigoVariableYUsuario(Long idVariable, Usuario usuario);
}
