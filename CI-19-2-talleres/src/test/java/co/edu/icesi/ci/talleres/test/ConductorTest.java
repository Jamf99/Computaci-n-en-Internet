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
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

@SpringBootTest(classes = Ci192TalleresApplication.class)
@RunWith(SpringRunner.class)
@Rollback
public class ConductorTest {

	@Autowired
	private IConductorDAO conductorDao;
	
	@Autowired
	private IRutaDAO rutaDao;
	
	@Autowired
	private IBusDAO busDao;
	
	@Autowired
	private IServicioDAO servicioDao;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public void escenarioUno() {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("123456789");
		conductor.setNombre("Juan Manuel");
		conductor.setApellidos("Ipachi");
		conductor.setFechaContratacion(new Date());
		conductor.setFechaNacimiento(new Date());
		conductorDao.save(conductor);
	}
	
	public void escenarioDos() {
		Tmio1Conductore conductor1 = new Tmio1Conductore();
		conductor1.setCedula("987654321");
		conductor1.setNombre("Pachito");
		conductor1.setApellidos("Restrepo");
		conductor1.setFechaContratacion(new Date());
		conductor1.setFechaNacimiento(new Date());
		conductorDao.save(conductor1);
		
		Tmio1Conductore conductor2 = new Tmio1Conductore();
		conductor2.setCedula("192837465");
		conductor2.setNombre("Cristian");
		conductor2.setApellidos("Pechene");
		conductor2.setFechaContratacion(new Date());
		conductor2.setFechaNacimiento(new Date());
		conductorDao.save(conductor2);
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
	public void buscarConductoresConServiciosTest() throws ParseException {
	escenarioTres();
	Date fecha= format.parse("2018-12-17");
		assertNotNull(conductorDao.buscarConductoresConServicios(fecha).get(0));

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveConductor() {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1143878270");
		conductor.setNombre("Jorge Antonio");
		conductor.setApellidos("Morales Flórez");
		conductor.setFechaContratacion(new Date());
		conductor.setFechaNacimiento(new Date());
		conductorDao.save(conductor);
		
		assertNotNull(conductorDao.findById(conductor.getCedula()));
		assertEquals("1143878270", conductorDao.findById(conductor.getCedula()).getCedula());
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteConductor() {
		escenarioUno();
		conductorDao.delete(conductorDao.findAll().get(0));
		assertTrue(conductorDao.findAll().size() == 0);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBuscarPorNombre() {
		escenarioDos();
		assertNotNull(conductorDao.buscarPorNombre("Cristian").get(0));
		assertTrue(conductorDao.buscarPorNombre("Sarna").isEmpty());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBuscarPorApellidos() {
		escenarioDos();
		assertNotNull(conductorDao.buscarPorApellidos("Restrepo").get(0));
		assertTrue(conductorDao.buscarPorApellidos("Rodriguez").isEmpty());
	}
	
//	@Test
//	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void testUpdateConductor() {
//		
//	}

}
