package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.ResultadoVariable;
import ec.org.grupofaro.modelo.Usuario;
import ec.org.grupofaro.repositorio.ResultadoVariableRepositorio;

@Service
public class ResultadoVariableServiceImpl implements ResultadoVariableService {

	@Autowired
	ResultadoVariableRepositorio resultadoVariableRepositorio;

	@Override
	public void guardar(ResultadoVariable resultadoVariable) {
		resultadoVariableRepositorio.save(resultadoVariable);
	}

	@Override
	public List<ResultadoVariable> buscarPorUusario(Usuario usuario) {
		return resultadoVariableRepositorio.findByUsuario(usuario);
	}

	@Override
	public Optional<ResultadoVariable> buscarPorCodigoVariableYUsuario(Long idVariable, Usuario usuario) {
		return resultadoVariableRepositorio.findByCodigoVariableAndUsuario(idVariable, usuario);
	}

}
