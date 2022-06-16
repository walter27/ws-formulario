package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.org.grupofaro.modelo.Usuario;
import ec.org.grupofaro.repositorio.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public void guardarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Override
	public List<Usuario> listarUsuario() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		return usuarioRepository.findById(id);
	}

}
