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
public class RutaTest {

	@Autowired
	private IRutaDAO rutaDao;
	
	@Autowired
	private IBusDAO busDao;
	
	@Autowired
	private IConductorDAO conductorDao;
	
	@Autowired
	private IServicioDAO servicioDao;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public void escenarioUno() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(100));
		ruta.setHoraFin(new BigDecimal(500));
		ruta.setNumero("1");
		rutaDao.save(ruta);
	}
	
	public void escenarioDos() {
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setDiaInicio(new BigDecimal(2));
		ruta1.setDiaFin(new BigDecimal(3));
		ruta1.setHoraInicio(new BigDecimal(200));
		ruta1.setHoraFin(new BigDecimal(400));
		ruta1.setNumero("2");
		rutaDao.save(ruta1);
		
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setDiaInicio(new BigDecimal(4));
		ruta2.setDiaFin(new BigDecimal(7));
		ruta2.setHoraInicio(new BigDecimal(300));
		ruta2.setHoraFin(new BigDecimal(500));
		ruta2.setNumero("3");
		rutaDao.save(ruta2);
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
	public void buscarConMenosDe10ServiciosTest() throws ParseException {
		escenarioTres();
		Date fecha = format.parse("2018-12-15");
		assertEquals(1, rutaDao.buscarConMenosDe10Servicios(fecha).size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveRuta() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(100));
		ruta.setHoraFin(new BigDecimal(500));
		ruta.setNumero("1");
		rutaDao.save(ruta);
		
		assertNotNull(rutaDao.findById(ruta.getId()));
		assertEquals("1", rutaDao.findById(ruta.getId()).getNumero());
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteRuta() {
		escenarioUno();
		rutaDao.delete(rutaDao.findAll().get(0));
		assertTrue(rutaDao.findAll().size() == 0);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBuscarPorIntervaloDeFecha() {
		escenarioDos();
		assertNotNull(rutaDao.buscarPorRangoDeFechas(new BigDecimal(4), new BigDecimal(7)).get(0));
		assertTrue(rutaDao.buscarPorRangoDeFechas(new BigDecimal(6), new BigDecimal(7)).isEmpty());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBuscarPorIntervaloDeHora() {
		escenarioDos();
		assertNotNull(rutaDao.buscarPorRangoDeHoras(new BigDecimal(200), new BigDecimal(400)).get(0));
		assertTrue(rutaDao.buscarPorRangoDeHoras(new BigDecimal(1000), new BigDecimal(1200)).isEmpty());
	}
	
//	@Test
//	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void testUpdateConductor() {
//		
//	}

}
