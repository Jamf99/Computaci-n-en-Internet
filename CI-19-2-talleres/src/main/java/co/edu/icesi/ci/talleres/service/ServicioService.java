package co.edu.icesi.ci.talleres.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.IServicioDAO;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;


@Service
public class ServicioService implements IServicioService {

	private IServicioDAO servicioDao;

	@Autowired
	public ServicioService(IServicioDAO servicioDao) {
		this.servicioDao   = servicioDao;
	}

	@Transactional
	@Override
	public void save(Tmio1Servicio servicio) {
		if(servicio.getId().getFechaInicio().before(servicio.getId().getFechaFin())) {
			servicioDao.save(servicio);
		}
	}

	@Transactional
	@Override
	public Tmio1Servicio findById(Integer idHash) {
		return servicioDao.findById(idHash);
	}
	
	@Transactional
	public Tmio1Servicio buscarPorId(Integer id) {
		Iterable<Tmio1Servicio> todos = findAll();
		for(Tmio1Servicio servicio : todos) {
			if(servicio.getIdHash().equals(id)) {
				return servicio;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public Iterable<Tmio1Servicio> findAll() {
		return servicioDao.findAll();
	}

	@Transactional
	@Override
	public void delete(Tmio1Servicio servicio) {
		servicioDao.delete(servicio);
	}

	

}
