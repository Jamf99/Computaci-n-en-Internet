package co.edu.icesi.ci.talleres.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tmio1_conductores database table.
 * 
 */
@Data
@Entity
@Table(name="tmio1_conductores")
@NamedQuery(name="Tmio1Conductore.findAll", query="SELECT t FROM Tmio1Conductore t")
public class Tmio1Conductore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank
	private String cedula;
	
	@NotBlank
	private String apellidos;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_contratacion")
	private Date fechaContratacion;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past 
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	@NotBlank
	private String nombre;

	//bi-directional many-to-one association to Tmio1Servicio
	@OneToMany(mappedBy="tmio1Conductore")
	private List<Tmio1Servicio> tmio1Servicios;

	//bi-directional many-to-one association to Tmio1ServiciosSitio
	@OneToMany(mappedBy="tmio1Conductore")
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitios;

	public Tmio1Conductore() {
	}

	public List<Tmio1Servicio> getTmio1Servicios() {
		return this.tmio1Servicios;
	}

	public void setTmio1Servicios(List<Tmio1Servicio> tmio1Servicios) {
		this.tmio1Servicios = tmio1Servicios;
	}

	public Tmio1Servicio addTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().add(tmio1Servicio);
		tmio1Servicio.setTmio1Conductore(this);

		return tmio1Servicio;
	}

	public Tmio1Servicio removeTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().remove(tmio1Servicio);
		tmio1Servicio.setTmio1Conductore(null);

		return tmio1Servicio;
	}

	public List<Tmio1ServiciosSitio> getTmio1ServiciosSitios() {
		return this.tmio1ServiciosSitios;
	}

	public void setTmio1ServiciosSitios(List<Tmio1ServiciosSitio> tmio1ServiciosSitios) {
		this.tmio1ServiciosSitios = tmio1ServiciosSitios;
	}

	public Tmio1ServiciosSitio addTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().add(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Conductore(this);

		return tmio1ServiciosSitio;
	}

	public Tmio1ServiciosSitio removeTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().remove(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Conductore(null);

		return tmio1ServiciosSitio;
	}
	

}