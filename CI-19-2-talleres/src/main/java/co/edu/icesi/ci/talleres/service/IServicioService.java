package co.edu.icesi.ci.talleres.service;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;

public interface IServicioService {

	public void save(Tmio1Servicio servicio);

	public Tmio1Servicio findById(Integer id);

	public Iterable<Tmio1Servicio> findAll();

	public void delete(Tmio1Servicio servicio);
	
}
