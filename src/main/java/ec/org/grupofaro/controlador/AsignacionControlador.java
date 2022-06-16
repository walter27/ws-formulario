package ec.org.grupofaro.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.grupofaro.modelo.Asignacion;
import ec.org.grupofaro.modelo.Usuario;
import ec.org.grupofaro.modelo.Variante;
import ec.org.grupofaro.servicio.AsignacionService;
import ec.org.grupofaro.servicio.UsuarioService;
import ec.org.grupofaro.servicio.VarianteService;

@RestController
@RequestMapping("/asignacion")
public class AsignacionControlador {

	@Autowired
	VarianteService varianteService;

	@Autowired
	AsignacionService asignacionService;

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/registrar/{idUsuario}")
	public ResponseEntity<?> registrarAsignaciones(@PathVariable Long idUsuario,
			@RequestBody List<Asignacion> listaAsignacion) {
		try {
			Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(idUsuario);
			if (usuarioOptional.isPresent()) {
				List<Variante> listaVariante = varianteService.listaVariante();
				for (Variante variante : listaVariante) {
					List<Asignacion> listaAsignacionVariante = listaAsignacion.stream()
							.filter(asignacion -> asignacion.getCodigoVariante().equals(variante.getId().toString()))
							.collect(Collectors.toList());

					for (Asignacion asignacion : listaAsignacionVariante) {
						variante.getListaAsignacion().add(asignacion);
						asignacion.setCodigoUsuario(idUsuario);
						asignacionService.guardar(asignacion);
					}
					listaAsignacionVariante = new ArrayList<>();
				}
				Map<String, Object> body = new HashMap<>();
				body.put("estado", true);
				body.put("mensaje", "Asignaciones registradas");
				return new ResponseEntity<>(body, HttpStatus.OK);
			} else {
				Map<String, Object> body = new HashMap<>();
				body.put("estado", true);
				body.put("mensaje", "Usuario no registrado");
				return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> body = new HashMap<>();
			body.put("estado", false);
			body.put("mensaje", "Error interno");
			return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/listar")
	ResponseEntity<?> listarAsignacion() {
		try {
			List<Asignacion> listaAsignacion = asignacionService.listaAsignacion();
			if (listaAsignacion.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(listaAsignacion);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
		}
	}

	@GetMapping("/listar/{idUsuario}")
	ResponseEntity<?> listarAsignacionPorUsuario(@PathVariable Long idUsuario) {
		try {
			Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(idUsuario);
			if (usuarioOptional.isPresent()) {
				List<Asignacion> listaAsignacion = asignacionService.listarPorUsuario(idUsuario);
				if (listaAsignacion.size() > 0) {
					return ResponseEntity.status(HttpStatus.OK).body(listaAsignacion);
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
		}
	}

}
