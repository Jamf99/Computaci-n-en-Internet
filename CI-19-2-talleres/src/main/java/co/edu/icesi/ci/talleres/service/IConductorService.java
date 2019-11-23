package co.edu.icesi.ci.talleres.service;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface IConductorService {

	public void save(Tmio1Conductore conductor);

	public Tmio1Conductore findById(String cedula);

	public Iterable<Tmio1Conductore> findAll();

	public void delete(Tmio1Conductore conductor);
	
}
