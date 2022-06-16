package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.Variable;
import ec.org.grupofaro.repositorio.VariableRepository;

@Service
public class VariableServiceImpl implements VariableService {

	@Autowired
	VariableRepository variableRepository;

	@Override
	public Optional<Variable> buscarPorId(Long id) {
		return variableRepository.findById(id);
	}

	@Override
	public List<Variable> listaVariable() {
		return variableRepository.findAll();
	}

}
