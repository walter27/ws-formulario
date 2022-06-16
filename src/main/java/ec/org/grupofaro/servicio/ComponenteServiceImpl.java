package ec.org.grupofaro.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.Componente;
import ec.org.grupofaro.repositorio.ComponenteRepositorio;

@Service
public class ComponenteServiceImpl implements ComponenteService {

	@Autowired
	ComponenteRepositorio componenteRepositorio;

	@Override
	public List<Componente> listarComponente() {
		return componenteRepositorio.findAll();
	}

}
