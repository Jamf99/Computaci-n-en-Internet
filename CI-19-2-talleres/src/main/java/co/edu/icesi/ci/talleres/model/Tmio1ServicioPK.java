package co.edu.icesi.ci.talleres.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * The primary key class for the tmio1_servicios database table.
 * 
 */
@Data
@Embeddable
public class Tmio1ServicioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="id_ruta", insertable=false, updatable=false)
	private Integer idRuta;

	@Column(name="cedula_conductor", insertable=false, updatable=false)
	private String cedulaConductor;

	@Column(name="id_bus", insertable=false, updatable=false)
	private Integer idBus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	public Tmio1ServicioPK() {
		
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tmio1ServicioPK)) {
			return false;
		}
		Tmio1ServicioPK castOther = (Tmio1ServicioPK)other;
		return 
			this.idRuta.equals(castOther.idRuta)
			&& this.cedulaConductor.equals(castOther.cedulaConductor)
			&& this.idBus.equals(castOther.idBus)
			&& this.fechaInicio.equals(castOther.fechaInicio)
			&& this.fechaFin.equals(castOther.fechaFin);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRuta.hashCode();
		hash = hash * prime + this.cedulaConductor.hashCode();
		hash = hash * prime + this.idBus.hashCode();
		hash = hash * prime + this.fechaInicio.hashCode();
		hash = hash * prime + this.fechaFin.hashCode();
		return hash;
	}
}