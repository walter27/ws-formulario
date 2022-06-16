package ec.org.grupofaro.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.grupofaro.modelo.Componente;
import ec.org.grupofaro.servicio.ComponenteService;

@RestController
@RequestMapping("/componente")
public class ComponenteController {

	@Autowired
	ComponenteService componenteService;

	@GetMapping("/listar")
	ResponseEntity<?> listarComponente() {
		try {
			List<Componente> listaComponente = componenteService.listarComponente();
			if (listaComponente.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(listaComponente);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
		}
	}

}
