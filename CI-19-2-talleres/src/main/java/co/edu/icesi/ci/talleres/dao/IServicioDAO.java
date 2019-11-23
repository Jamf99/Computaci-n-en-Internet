package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;

public interface IServicioDAO {
	
	public void save(Tmio1Servicio entity);
	public void update(Tmio1Servicio entity);
	public void delete(Tmio1Servicio entity);
	public Tmio1Servicio findById(Integer idHash);
	public List<Tmio1Servicio> findAll();

}
