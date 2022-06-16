package ec.org.grupofaro.controlador;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.org.grupofaro.modelo.Asignacion;
import ec.org.grupofaro.modelo.ResultadoVariante;
import ec.org.grupofaro.modelo.Usuario;
import ec.org.grupofaro.modelo.Variante;
import ec.org.grupofaro.servicio.AsignacionService;
import ec.org.grupofaro.servicio.ResultadoVarianteService;
import ec.org.grupofaro.servicio.UsuarioService;
import ec.org.grupofaro.servicio.VariableService;
import ec.org.grupofaro.servicio.VarianteService;

@RestController
@RequestMapping("/variante")
public class VarianteControlador {

	@Autowired
	VarianteService varianteService;

	@Autowired
	AsignacionService asignacionService;

	@Autowired
	VariableService variableService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ResultadoVarianteService resultadoVarianteService;

	@Value("${directorio.archivo.carga}")
	private String TEMP;

	@GetMapping("/listar")
	public ResponseEntity<?> listarVariante() {
		try {
			List<Variante> listaVariante = varianteService.listaVariante();
			if (listaVariante.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(listaVariante);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No existe variantes");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar");
		}
	}

	@PostMapping("/registrar/verdaderas")
	public ResponseEntity<?> registrarAsignacionesVerdaderas(@RequestPart("listaAsignacion") String listaAsignacionJSON,
			@RequestParam(value = "archivos") List<MultipartFile> listaArchivo) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Asignacion> listaAsignacion = objectMapper.readValue(listaAsignacionJSON,
					new TypeReference<List<Asignacion>>() {
					});
			for (Asignacion asignacion : listaAsignacion) {
				Optional<Asignacion> optionalAsignacion = asignacionService.buscarPorId(asignacion.getId());
				for (MultipartFile archivo : listaArchivo) {
					if (optionalAsignacion.isPresent()) {
						if (asignacion.getArchivoPath().equals(obtenerNombreArchivo(archivo))) {
							InputStream inputStream = archivo.getInputStream();
							try {
								String nombreArchivo = UUID.randomUUID().toString() + "_-_"
										+ archivo.getOriginalFilename();
								Path pathFile = getPath(nombreArchivo);

								if (optionalAsignacion.get().getArchivoPath().length() > 0) {
									eliminarArchivo(optionalAsignacion.get().getArchivoPath());
									optionalAsignacion.get().setArchivoPath(pathFile.toString());
								}
								if (optionalAsignacion.get().getArchivoPath().equals("")) {
									optionalAsignacion.get().setArchivoPath(pathFile.toString());
								}
								optionalAsignacion.get().setUrl(asignacion.getUrl());
								optionalAsignacion.get().setSeleccionado(asignacion.isSeleccionado());
								Files.copy(inputStream, pathFile);
							} finally {
								if (inputStream != null) {
									inputStream.close();
								}
							}
							asignacionService.guardar(optionalAsignacion.get());
						} else {
							if (!asignacion.getArchivoPath().equals(optionalAsignacion.get().getArchivoPath())
									&& asignacion.getArchivoPath().equals("")) {
								eliminarArchivo(optionalAsignacion.get().getArchivoPath());
								optionalAsignacion.get().setArchivoPath("");
							}
							if (asignacion.getArchivoPath().equals("")) {
								optionalAsignacion.get().setSeleccionado(asignacion.isSeleccionado());
							}
							optionalAsignacion.get().setUrl(asignacion.getUrl());
							asignacionService.guardar(optionalAsignacion.get());

						}
					}
				}
			}
			Map<String, Object> body = new HashMap<>();
			body.put("estado", true);
			body.put("mensaje", "Respuestas registradas");
			return new ResponseEntity<>(body, HttpStatus.OK);

		} catch (Exception e) {
			Map<String, Object> body = new HashMap<>();
			body.put("estado", false);
			body.put("mensaje", "Error interno");
			return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/registrar/falsas")
	public ResponseEntity<?> registrarAsignacionesFalsas(@RequestPart("listaAsignacion") String listaAsignacionJSON) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Asignacion> listaAsignacion = objectMapper.readValue(listaAsignacionJSON,
					new TypeReference<List<Asignacion>>() {
					});
			for (Asignacion asignacion : listaAsignacion) {
				Optional<Asignacion> optionalAsignacion = asignacionService.buscarPorId(asignacion.getId());
				if (optionalAsignacion.isPresent()) {
					if (optionalAsignacion.get().getArchivoPath().length() > 0) {
						eliminarArchivo(optionalAsignacion.get().getArchivoPath());
						optionalAsignacion.get().setArchivoPath("");
					}
					optionalAsignacion.get().setUrl(asignacion.getUrl());
					optionalAsignacion.get().setSeleccionado(asignacion.isSeleccionado());
					asignacionService.guardar(optionalAsignacion.get());
				}
			}
			Map<String, Object> body = new HashMap<>();
			body.put("estado", true);
			body.put("mensaje", "Respuestas registradas");
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> body = new HashMap<>();
			body.put("estado", false);
			body.put("mensaje", "Error interno");
			return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/registrar/asignaciones")
	public ResponseEntity<?> registrarAsignaciones(@RequestPart("listaAsignacion") String listaAsignacionJSON) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Asignacion> listaAsignacion = objectMapper.readValue(listaAsignacionJSON,
					new TypeReference<List<Asignacion>>() {
					});
			for (Asignacion asignacion : listaAsignacion) {
				Optional<Asignacion> optionalAsignacion = asignacionService.buscarPorId(asignacion.getId());
				if (optionalAsignacion.isPresent()) {
					if (!asignacion.getArchivoPath().equals(optionalAsignacion.get().getArchivoPath())) {
						eliminarArchivo(optionalAsignacion.get().getArchivoPath());
						optionalAsignacion.get().setArchivoPath("");
					}
					optionalAsignacion.get().setUrl(asignacion.getUrl());
					optionalAsignacion.get().setSeleccionado(asignacion.isSeleccionado());
					asignacionService.guardar(optionalAsignacion.get());
				}
			}
			Map<String, Object> body = new HashMap<>();
			body.put("estado", true);
			body.put("mensaje", "Respuestas registradas");
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> body = new HashMap<>();
			body.put("estado", false);
			body.put("mensaje", "Error interno");
			return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/calcular/normalizacion/{idUsuario}")
	public ResponseEntity<?> calcularNormalizacion(@PathVariable Long idUsuario) {
		try {
			Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(idUsuario);
			List<Variante> listaVariante = varianteService.listaVariante();
			for (Variante variante : listaVariante) {
				Optional<ResultadoVariante> resultadoVarianteOptional = resultadoVarianteService
						.buscarPorCodigoVarianteYPorUsuario(variante.getId(), usuarioOptional.get());
				if (!resultadoVarianteOptional.isPresent()) {
					ResultadoVariante resultadoVariante = new ResultadoVariante();
					guardarResultadoVariante(variante, resultadoVariante, usuarioOptional, idUsuario);
				} else {
					guardarResultadoVariante(variante, resultadoVarianteOptional.get(), usuarioOptional, idUsuario);
				}

			}
			List<ResultadoVariante> listaResultadoVariante = resultadoVarianteService.listar().stream()
					.filter(resultadoVariante -> resultadoVariante.getUsuario().getId().equals(idUsuario))
					.collect(Collectors.toList());
			OptionalInt maximo = listaResultadoVariante.stream()
					.mapToInt(resultadoVariante -> resultadoVariante.getResultado()).max();
			OptionalInt minimo = listaResultadoVariante.stream()
					.mapToInt(resultadoVariante -> resultadoVariante.getResultado()).min();
			for (ResultadoVariante resultadoVarianteFinal : listaResultadoVariante) {
				resultadoVarianteFinal.setNormalizacion(new BigDecimal(
						(Double.valueOf(resultadoVarianteFinal.getResultado()) - Double.valueOf(minimo.getAsInt()))
								/ (Double.valueOf(maximo.getAsInt()) - Double.valueOf(minimo.getAsInt()))));
				resultadoVarianteService.guardar(resultadoVarianteFinal);
			}

			Map<String, Object> body = new HashMap<>();
			body.put("estado", true);
			body.put("mensaje", "Cálculo realizado");
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> body = new HashMap<>();
			body.put("estado", false);
			body.put("mensaje", "Error interno");
			return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar/normalizacion/{idUsuario}")
	public ResponseEntity<?> buscarNormalizacion(@PathVariable Long idUsuario) {
		try {

			Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(idUsuario);
			List<ResultadoVariante> listaResutadoVariante = resultadoVarianteService
					.listarPorUusario(usuarioOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body(listaResutadoVariante);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar resultados");
		}
	}

	public void guardarResultadoVariante(Variante variante, ResultadoVariante resultadoVariante,
			Optional<Usuario> usuarioOptional, Long idUsuario) {

		Integer resultado = variante.getListaAsignacion().stream().filter(
				asignacion -> asignacion.isSeleccionado() == true && asignacion.getCodigoUsuario().equals(idUsuario))
				.mapToInt(asignacion -> asignacion.getPuntuacion()).sum();
		resultadoVariante.setUsuario(usuarioOptional.get());
		resultadoVariante.setResultado(resultado);
		resultadoVariante.setCodigoVariante(variante.getId());
		resultadoVariante.setCodigoVariable(Long.valueOf(variante.getCodigoVariable()));
		resultadoVarianteService.guardar(resultadoVariante);

	}

	public String obtenerNombreArchivo(MultipartFile archivo) {
		return archivo.getOriginalFilename();
	}

	public Path getPath(String fileName) {
		return Paths.get(TEMP).resolve(fileName).toAbsolutePath();
	}

	public void eliminarArchivo(String path) {
		File file = new File(path);
		if (file.exists() && file.canRead()) {
			file.delete();
		}
	}
}
