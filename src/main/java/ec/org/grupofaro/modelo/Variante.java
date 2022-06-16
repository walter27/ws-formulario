package ec.org.grupofaro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FAR_VARIANTE")
public class Variante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VAR_ID_PR")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "COD_COMP")
	private String codigoComponente;
	
	@Column(name = "COD_VAR")
	private String codigoVariable;

	@OneToMany(fetch = FetchType.LAZY) 
	List<Asignacion> listaAsignacion;

	public Variante() {
		this.listaAsignacion = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Asignacion> getListaAsignacion() {
		return listaAsignacion;
	}

	public void setListaAsignacion(List<Asignacion> listaAsignacion) {
		this.listaAsignacion = listaAsignacion;
	}

	public void agregarAsignacion(Asignacion asignacion) {
		this.listaAsignacion.add(asignacion);
	}

	public String getCodigoComponente() {
		return codigoComponente;
	}

	public void setCodigoComponente(String codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

	public String getCodigoVariable() {
		return codigoVariable;
	}

	public void setCodigoVariable(String codigoVariable) {
		this.codigoVariable = codigoVariable;
	}

}
