package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.ResultadoVariante;
import ec.org.grupofaro.modelo.Usuario;
import ec.org.grupofaro.repositorio.ResultadoVarianteRepositorio;

@Service
public class ResultadoVarianteServiceImpl implements ResultadoVarianteService {

	@Autowired
	ResultadoVarianteRepositorio resultadoVarianteRepositorio;

	@Override
	public void guardar(ResultadoVariante resultadoVariante) {
		resultadoVarianteRepositorio.save(resultadoVariante);
	}

	@Override
	public List<ResultadoVariante> listar() {
		return resultadoVarianteRepositorio.findAll();
	}

	@Override
	public List<ResultadoVariante> listarPorUusario(Usuario usuario) {
		return resultadoVarianteRepositorio.findByUsuario(usuario);
	}

	@Override
	public Optional<ResultadoVariante> buscarPorCodigoVarianteYPorUsuario(Long idVariante, Usuario usuario) {
		return resultadoVarianteRepositorio.findByCodigoVarianteAndUsuario(idVariante, usuario);
	}

}
