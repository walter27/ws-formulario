package ec.org.grupofaro.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FAR_RESULTADO_VARIABLE")
public class ResultadoVariable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RES_VAR_ID_PR")
	private Long id;

	@Column(name = "COD_VAR")
	private Long codigoVariable;

	@JoinColumn(name = "COD_USU")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	private BigDecimal normalizacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigoVariable() {
		return codigoVariable;
	}

	public void setCodigoVariable(Long codigoVariable) {
		this.codigoVariable = codigoVariable;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getNormalizacion() {
		return normalizacion.setScale(2, RoundingMode.HALF_UP);
	}

	public void setNormalizacion(BigDecimal normalizacion) {
		this.normalizacion = normalizacion.setScale(2, RoundingMode.HALF_UP);
	}

}
