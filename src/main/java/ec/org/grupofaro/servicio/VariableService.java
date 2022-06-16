package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import ec.org.grupofaro.modelo.Variable;

public interface VariableService {
	
	Optional<Variable> buscarPorId(Long id);
	
	List<Variable> listaVariable();
}
