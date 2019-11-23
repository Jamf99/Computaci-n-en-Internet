package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface IRutaDAO {
	
	public void save(Tmio1Ruta entity);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	public Tmio1Ruta findById(Integer id);
	public List<Tmio1Ruta> findAll();
	List<Tmio1Ruta> buscarPorRangoDeHoras(BigDecimal horaInicio, BigDecimal horaFin);
	List<Tmio1Ruta> buscarPorRangoDeFechas(BigDecimal diaInicio, BigDecimal diaFin);
	List<Tmio1Ruta> buscarConMenosDe10Servicios(Date fecha);

}
