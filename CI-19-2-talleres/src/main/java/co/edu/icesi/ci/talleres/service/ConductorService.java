package co.edu.icesi.ci.talleres.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.IConductorDAO;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

@Service
public class ConductorService implements IConductorService {

	@Autowired
	private IConductorDAO conductorDao;
	
	public ConductorService(IConductorDAO conductorDao) {
		this.conductorDao = conductorDao;
	}
	
	@Transactional
	public void save(Tmio1Conductore conductor) {
		if(conductor.getFechaNacimiento().before(conductor.getFechaContratacion())) {
			conductorDao.save(conductor);
		}
	}

	@Transactional
	public Tmio1Conductore findById(String cedula) {

		return conductorDao.findById(cedula);
	}

	@Transactional
	public Iterable<Tmio1Conductore> findAll() {
		return conductorDao.findAll();
	}
	
	@Transactional
	public void delete(Tmio1Conductore conductor) {
		conductorDao.delete(conductor);

	}


}
