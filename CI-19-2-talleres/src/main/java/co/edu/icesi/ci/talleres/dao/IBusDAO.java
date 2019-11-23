package co.edu.icesi.ci.talleres.dao;

import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface IBusDAO {
	
	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	public Tmio1Bus findById(Integer codigo);
	public List<Tmio1Bus> findAll();
	List<Tmio1Bus> buscarPorPlaca(String placa);
	List<Tmio1Bus> buscarPorMarca(String marca);
	List<Tmio1Bus> buscarPorModelo(int modelo);
	List<Tmio1Bus> buscarDatosServicioVigencia(Date fecha);

}
