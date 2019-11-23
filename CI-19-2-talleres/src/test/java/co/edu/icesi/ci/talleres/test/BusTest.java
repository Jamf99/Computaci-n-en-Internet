package co.edu.icesi.ci.talleres.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.talleres.Ci192TalleresApplication;
import co.edu.icesi.ci.talleres.dao.IBusDAO;
import co.edu.icesi.ci.talleres.dao.IConductorDAO;
import co.edu.icesi.ci.talleres.dao.IRutaDAO;
import co.edu.icesi.ci.talleres.dao.IServicioDAO;
import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

@SpringBootTest(classes = Ci192TalleresApplication.class)
@RunWith(SpringRunner.class)
@Rollback
public class BusTest {

	@Autowired
	private IBusDAO busDao;
	
	@Autowired
	private IConductorDAO conductorDao;
	
	@Autowired
	private IRutaDAO rutaDao;
	
	@Autowired
	private IServicioDAO servicioDao;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public void escenarioUno() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setPlaca("ABC123");
		bus.setMarca("Mercedes Benz");
		bus.setCapacidad(1000.0);
		bus.setModelo(2014);
		busDao.save(bus);
	}
	
	public void escenarioDos() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setPlaca("OLA123");
		bus.setMarca("Mercedes Benz");
		bus.setCapacidad(5000.0);
		bus.setModelo(2015);
		busDao.save(bus);
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setPlaca("XYZ123");
		bus2.setMarca("Marcopolo");
		bus2.setCapacidad(2000.0);
		bus2.setModelo(2017);
		busDao.save(bus2);
	}
	
	public void escenarioTres() throws ParseException {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(20));
		ruta.setDiaFin(new BigDecimal(26));
		ruta.setNumero("12345");
		ruta.setHoraInicio(new BigDecimal(180));
		ruta.setHoraFin(new BigDecimal(200));
		rutaDao.save(ruta);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setMarca("MercedesBenz");
		bus.setModelo(2016);
		bus.setPlaca("URZ123");
		bus.setCapacidad(2000.0);
		busDao.save(bus);
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1143878270");
		conductor.setApellidos("Morales Flórez");
		conductor.setNombre("Jorge Antonio");
		conductor.setFechaNacimiento(format.parse("1999-05-03"));
		conductor.setFechaContratacion(format.parse("2017-04-01"));
		conductorDao.save(conductor);
		
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor("1143878270");
		pk.setIdBus(bus.getId());
		pk.setIdRuta(ruta.getId());
		pk.setFechaInicio(format.parse("2018-12-10"));
		pk.setFechaFin(format.parse("2018-12-20"));
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setId(pk);
		servicio.setTmio1Ruta(ruta);
		servicioDao.save(servicio);
		
		pk = new Tmio1ServicioPK();
		pk.setCedulaConductor("1143878270");
		pk.setIdBus(bus.getId());
		pk.setIdRuta(ruta.getId());
		pk.setFechaInicio(format.parse("2018-12-05"));
		pk.setFechaFin(format.parse("2018-12-25"));
		servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setId(pk);
		servicio.setTmio1Ruta(ruta);
		servicioDao.save(servicio);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buscarDatosServicioVigenciaTest() throws ParseException {
		escenarioTres();
		Date fecha;
		try {
			fecha = format.parse("2018-12-15");
			assertEquals(1, busDao.buscarDatosServicioVigencia(fecha).size());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveBus() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setPlaca("ABC123");
		bus.setMarca("Mercedes Benz");
		bus.setCapacidad(1000.0);
		bus.setModelo(2014);
		bus.setTipo(BusType.T);
		busDao.save(bus);
		
		assertNotNull(busDao.findById(bus.getId()));
		assertEquals("ABC123", busDao.findById(bus.getId()).getPlaca());
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteBus() {
		escenarioUno();
		busDao.delete(busDao.findAll().get(0));
		assertTrue(busDao.findAll().size() == 0);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBuscarPorMarca() {
		escenarioDos();
		assertNotNull(busDao.buscarPorMarca("Mercedes Benz").get(0));
		assertTrue(busDao.buscarPorMarca("Panela").isEmpty());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBuscarPorPlaca() {
		escenarioDos();
		assertNotNull(busDao.buscarPorPlaca("XYZ123").get(0));
		assertTrue(busDao.buscarPorPlaca("COC123").isEmpty());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBuscarPorModelo() {
		escenarioDos();
		assertNotNull(busDao.buscarPorModelo(2015).get(0));
		assertTrue(busDao.buscarPorModelo(1999).isEmpty());
	}
	
//	@Test
//	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void testUpdateBus() {
//		Tmio1Bus bus = new Tmio1Bus();
//		bus.setPlaca("ABC123");
//		bus.setMarca("Mercedes Benz");
//		bus.setCapacidad(1000.0);
//		bus.setModelo(2014);
//		bus.setTipo(BusType.T);
//		busDao.save(bus);
//		
//		busDao.update(bus);
//		
//		assertNotNull(busDao.buscarPorModelo(2015).get(0));
//	}


}
