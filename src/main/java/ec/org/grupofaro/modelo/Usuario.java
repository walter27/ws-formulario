package ec.org.grupofaro.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FAR_USUARIO")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USU_ID_PR")
	private Long id;

	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;

	@Column(name = "CONTRASENA")
	private String contrasena;

	@Column(name = "NOMBRE_GAD")
	private String nombreGad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombreGad() {
		return nombreGad;
	}

	public void setNombreGad(String nombreGad) {
		this.nombreGad = nombreGad;
	}

}
