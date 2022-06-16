package ec.org.grupofaro.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FAR_ASIGNACION")
public class Asignacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ASIG_ID_PR")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "SELECCIONADO")
	private boolean seleccionado;

	@Column(name = "ARCHIVO_PATH")
	private String archivoPath;

	@Column(name = "ARCHIVO_CONT")
	private boolean archivoContiene;

	@Column(name = "PUNTUACION")
	private Integer puntuacion;

	@Column(name = "COD_USU")
	private Long codigoUsuario;

	@Column(name = "URL")
	private String url;

	@Column(name = "COD_VARI")
	private String codigoVariante;

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

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getArchivoPath() {
		return archivoPath;
	}

	public void setArchivoPath(String archivoPath) {
		this.archivoPath = archivoPath;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public boolean isArchivoContiene() {
		return archivoContiene;
	}

	public void setArchivoContiene(boolean archivoContiene) {
		this.archivoContiene = archivoContiene;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCodigoVariante() {
		return codigoVariante;
	}

	public void setCodigoVariante(String codigoVariante) {
		this.codigoVariante = codigoVariante;
	}

}
