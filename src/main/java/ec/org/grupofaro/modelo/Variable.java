package ec.org.grupofaro.modelo;

import java.io.Serializable;
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
@Table(name = "FAR_VARIABLE")
public class Variable implements Serializable {

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

	@OneToMany(fetch = FetchType.LAZY)
	private List<SubVariable> listaSubVariable;

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

	public List<SubVariable> getListaSubVariable() {
		return listaSubVariable;
	}

	public void setListaSubVariable(List<SubVariable> listaSubVariable) {
		this.listaSubVariable = listaSubVariable;
	}

}
