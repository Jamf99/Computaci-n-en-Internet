package co.edu.icesi.ci.talleres.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

@Repository
@Scope("singleton")
@Transactional
public class BusDAO implements IBusDAO{
	
	//prueba Git

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Bus entity) {
		entityManager.persist(entity);	
	}

	@Override
	public void update(Tmio1Bus entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Bus entity) {
		entityManager.remove(entity);	
	}

	@Override
	public Tmio1Bus findById(Integer id) {
		return entityManager.find(Tmio1Bus.class, id);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> findAll() {
		String jpql = "Select a from Tmio1Bus a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> buscarPorPlaca(String placa) {
		String jpql = "Select a from Tmio1Bus a where a.placa = '"+placa+"'";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> buscarPorMarca(String marca) {
		String jpql = "Select a from Tmio1Bus a where a.marca = '"+marca+"'";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> buscarPorModelo(int modelo) {
		String jpql = "Select a from Tmio1Bus a where a.modelo = '"+modelo+"'";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	@Override
	public List<Tmio1Bus> buscarDatosServicioVigencia(Date fecha) {
		String jpql = "Select a from Tmio1Bus a where (SELECT count(s) FROM "
				+ "Tmio1Servicio s WHERE s.id.idBus=a.id AND s.id.fechaFin>:lafecha AND "
				+ ":lafecha>s.id.fechaInicio) > 1";
		TypedQuery<Tmio1Bus> q = entityManager.createQuery(jpql, Tmio1Bus.class);
		q.setParameter("lafecha", fecha);
		return q.getResultList();
	}


}
