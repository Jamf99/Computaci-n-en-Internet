package co.edu.icesi.ci.talleres.dao;

import java.util.Date;
import java.util.List;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface IConductorDAO {
	
	public void save(Tmio1Conductore entity);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	public Tmio1Conductore findById(String cedula);
	public List<Tmio1Conductore> findAll();
	List<Tmio1Conductore> buscarPorNombre(String nombre);
	List<Tmio1Conductore> buscarPorApellidos(String apellidos);
	List<Object[]> buscarConductoresConServicios(Date fecha);

}
