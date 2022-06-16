package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import ec.org.grupofaro.modelo.Variante;

public interface VarianteService {

	public void registrar(Variante variante);

	public Optional<Variante> buscarPorId(Long id);
	
	public List<Variante> listaVariante();

}
