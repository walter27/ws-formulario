package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.Variante;
import ec.org.grupofaro.repositorio.VarianteRepositorio;

@Service
public class VarianteServiceImpl implements VarianteService {

	@Autowired
	VarianteRepositorio varianteRepositorio;

	@Override
	public void registrar(Variante variante) {
		varianteRepositorio.save(variante);
	}

	@Override
	public Optional<Variante> buscarPorId(Long id) {
		return varianteRepositorio.findById(id);
	}

	@Override
	public List<Variante> listaVariante() {
		return varianteRepositorio.findAll();
	}

}
