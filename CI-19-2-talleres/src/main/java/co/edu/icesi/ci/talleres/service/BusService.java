package co.edu.icesi.ci.talleres.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.IBusDAO;
import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;

@Service
public class BusService implements IBusService {

	@Autowired
	private IBusDAO busDao;
	
	public BusService(IBusDAO busDao) {
		this.busDao = busDao;
	}
	
	@Transactional
	public void save(Tmio1Bus bus) {
		busDao.save(bus);
	}

	@Transactional
	public Tmio1Bus findById(Integer id) throws Exception{
		if(id == null) {
			throw new Exception("Error al encontrar el bus");
		}
		return busDao.findById(id);
	}

	@Transactional
	public Iterable<Tmio1Bus> findAll() {
		return busDao.findAll();
	}
	
	@Transactional
	public void delete(Tmio1Bus bus) {
		busDao.delete(bus);

	}
	
	@Transactional
	public BusType[] getBusTypes() {
		return BusType.values();
	}
}
