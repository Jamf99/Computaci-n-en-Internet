package co.edu.icesi.ci.talleres.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.IRutaDAO;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

@Service
public class RutaService implements IRutaService {

	@Autowired
	private IRutaDAO rutaDao;
	
	public RutaService(IRutaDAO rutaDao) {
		this.rutaDao = rutaDao;
	}
	
	@Transactional
	public void save(Tmio1Ruta ruta) {
		if(ruta.getDiaInicio().compareTo(ruta.getDiaFin()) == -1 && ruta.getHoraInicio().compareTo(ruta.getHoraFin()) == -1) {
			rutaDao.save(ruta);
		}
	}

	@Transactional
	public Tmio1Ruta findById(Integer id) {

		return rutaDao.findById(id);
	}

	@Transactional
	public Iterable<Tmio1Ruta> findAll() {
		return rutaDao.findAll();
	}
	
	@Transactional
	public void delete(Tmio1Ruta ruta) {
		rutaDao.delete(ruta);

	}

}
