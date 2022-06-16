package ec.org.grupofaro.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.grupofaro.modelo.Rol;
import ec.org.grupofaro.modelo.Usuario;
import ec.org.grupofaro.servicio.RolService;
import ec.org.grupofaro.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@PostMapping("/registrar/{id}")
	public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario usuarioDTO = new Usuario();
		usuarioDTO = usuario;
		try {
			Optional<Rol> optionalRol = rolService.buscarPorId(id);
			if (optionalRol.isPresent()) {
				Rol rol = optionalRol.get();
				rol.getListaUsuario().add(usuarioDTO);
				usuarioService.guardarUsuario(usuarioDTO);
				return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No existe el rol");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el usuario");
		}
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarUusario(@PathVariable Long id) {
		try {
			Optional<Usuario> optionalUsuario = usuarioService.buscarPorId(id);
			if (optionalUsuario.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(optionalUsuario.get());
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No existe el usuario");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar usuario");
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listarUsuario() {
		try {
			List<Usuario> listaUsuario = usuarioService.listarUsuario();
			if (listaUsuario.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(listaUsuario);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No existe usuarios");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar usuarios");
		}
	}

	@GetMapping("/inicio")
	public ResponseEntity<?> iniciar() {
		try {
			Map<String, Object> body = new HashMap<>();
			body.put("estado", true);
			body.put("mensaje", "Bienvenido");
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> body = new HashMap<>();
			body.put("estado", false);
			body.put("mensaje", "Error interno");
			return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
