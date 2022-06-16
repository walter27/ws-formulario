package ec.org.grupofaro.servicio;

import java.util.List;
import java.util.Optional;

import ec.org.grupofaro.modelo.Usuario;

public interface UsuarioService {

	public void guardarUsuario(Usuario usuario);

	public void eliminarUsuario(Usuario usuario);

	public List<Usuario> listarUsuario();
	
    public Optional<Usuario> buscarPorId(Long id);

}
