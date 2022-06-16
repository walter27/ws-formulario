package ec.org.grupofaro.controlador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.Op;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.grupofaro.modelo.ResultadoVariable;
import ec.org.grupofaro.modelo.ResultadoVariante;
import ec.org.grupofaro.modelo.Usuario;
import ec.org.grupofaro.modelo.Variable;
import ec.org.grupofaro.servicio.ResultadoVariableService;
import ec.org.grupofaro.servicio.ResultadoVarianteService;
import ec.org.grupofaro.servicio.UsuarioService;
import ec.org.grupofaro.servicio.VariableService;
import ec.org.grupofaro.servicio.VarianteService;

@RestController
@RequestMapping("/variable")
public class VariableControlador {

	@Autowired
	VariableService variableService;

	@Autowired
	ResultadoVarianteService resultadoVarianteService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ResultadoVariableService resultadoVariableService;

	@Autowired
	VarianteService varianteService;

	@GetMapping("/listar")
	ResponseEntity<?> listarComponente() {
		try {
			List<Variable> listaVariable = variableService.listaVariable();
			if (listaVariable.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(listaVariable);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
		}
	}

	@PostMapping("/calcular/normalizacion/{idUsuario}")
	public ResponseEntity<?> calcularNormalizacion(@PathVariable Long idUsuario) {
		try {
			Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(idUsuario);
			List<Variable> listaVariable = variableService.listaVariable();
			List<ResultadoVariante> listaResultadoVariante = resultadoVarianteService
					.listarPorUusario(usuarioOptional.get());
			for (Variable variable : listaVariable) {
				Optional<ResultadoVariable> resultadoVariabeOptional = resultadoVariableService
						.buscarPorCodigoVariableYUsuario(variable.getId(), usuarioOptional.get());
				if (!resultadoVariabeOptional.isPresent()) {
					ResultadoVariable resultadoVariable = new ResultadoVariable();
					guardarResultadoVariable(variable, listaResultadoVariante, resultadoVariable, usuarioOptional);
				} else {
					guardarResultadoVariable(variable, listaResultadoVariante, resultadoVariabeOptional.get(),
							usuarioOptional);
				}
			}
			Map<String, Object> body = new HashMap<>();
			body.put("estado", true);
			body.put("mensaje", "Cálculo realizado");
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
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
			List<ResultadoVariable> listaResutadoVariable = resultadoVariableService
					.buscarPorUusario(usuarioOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body(listaResutadoVariable);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar resultados");
		}
	}

	public void guardarResultadoVariable(Variable variable, List<ResultadoVariante> listaResultadoVariante,
			ResultadoVariable resultadoVariable, Optional<Usuario> usuarioOptional) {
		BigDecimal normalizacion = listaResultadoVariante.stream()
				.filter(resultadoVariante -> resultadoVariante.getCodigoVariable().equals(variable.getId()))
				.map(resultadoVariante -> resultadoVariante.getNormalizacion()).reduce(BigDecimal::add).get();
		BigDecimal conteo = new BigDecimal(listaResultadoVariante.stream()
				.filter(resultadoVarianteFilter -> resultadoVarianteFilter.getCodigoVariable().equals(variable.getId()))
				.collect(Collectors.toList()).size());
		resultadoVariable.setUsuario(usuarioOptional.get());
		resultadoVariable.setCodigoVariable(variable.getId());
		resultadoVariable.setNormalizacion(normalizacion.divide(conteo, 2, RoundingMode.HALF_UP));
		resultadoVariableService.guardar(resultadoVariable);
	}

}
