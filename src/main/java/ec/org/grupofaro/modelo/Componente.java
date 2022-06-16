package ec.org.grupofaro.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FAR_COMPONENTE")
public class Componente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMP_ID_PR")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Variable> listaVariables;

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

	public List<Variable> getListaVariables() {
		return listaVariables;
	}

	public void setListaVariables(List<Variable> listaVariables) {
		this.listaVariables = listaVariables;
	}

}
