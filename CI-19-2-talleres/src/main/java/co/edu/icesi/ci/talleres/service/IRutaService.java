package co.edu.icesi.ci.talleres.service;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface IRutaService {

	public void save(Tmio1Ruta ruta);

	public Tmio1Ruta findById(Integer id);

	public Iterable<Tmio1Ruta> findAll();

	public void delete(Tmio1Ruta ruta);
	
}
