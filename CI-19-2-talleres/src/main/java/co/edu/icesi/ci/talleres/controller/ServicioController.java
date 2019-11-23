 package co.edu.icesi.ci.talleres.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.service.BusService;
import co.edu.icesi.ci.talleres.service.ConductorService;
import co.edu.icesi.ci.talleres.service.RutaService;
import co.edu.icesi.ci.talleres.service.ServicioService;

@Controller
public class ServicioController {

	private ServicioService servicioService;
	private ConductorService conductorService;
	private BusService busService;
	private RutaService rutaService;
	private Iterable<Tmio1Conductore> conductores;
	private Iterable<Tmio1Bus> buses;
	private Iterable<Tmio1Ruta> rutas;
	
	@Autowired
	public ServicioController(ServicioService servicioService, ConductorService conductorService, BusService busService, RutaService rutaService) {
		this.servicioService = servicioService;
		this.conductorService = conductorService;
		this.rutaService = rutaService;
		this.busService = busService;
		conductores = conductorService.findAll();
		buses = busService.findAll();
		rutas = rutaService.findAll();
	}
	
	@GetMapping("/servicios/")
	public String indexServicio(Model model) {
		model.addAttribute("servicios", servicioService.findAll());
		model.addAttribute("servicioSearched", new Tmio1Servicio());
		return "servicios/index";
	}
		
	@GetMapping("/servicios/add/")
	public String addService(Model model) {
		model.addAttribute("servicio", new Tmio1ServicioPK());
		model.addAttribute("conductores", conductorService.findAll());
		model.addAttribute("buses", busService.findAll());
		model.addAttribute("rutas", rutaService.findAll());
		return "servicios/add-servicio";
	}

	@PostMapping("/servicios/add/")
	public String saveService(@Valid @ModelAttribute Tmio1ServicioPK tmio1ServicioPK, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) throws Exception {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("conductores", conductores);
				model.addAttribute("buses", buses);
				model.addAttribute("rutas", rutas);
				return "servicios/add-servicio";
			} else {
				Tmio1Servicio s = new Tmio1Servicio();
				s.setId(tmio1ServicioPK);
				s.setTmio1Conductore(conductorService.findById(tmio1ServicioPK.getCedulaConductor()));
				s.setTmio1Bus(busService.findById(tmio1ServicioPK.getIdBus()));
				s.setTmio1Ruta(rutaService.findById(tmio1ServicioPK.getIdRuta()));
				s.setIdHash(s.getId().hashCode());
				servicioService.save(s);
			}
		}
		return "redirect:/servicios/";
	}
	
	@GetMapping("/servicios/edit/{idHash}")
	public String showUpdateServices(@PathVariable("idHash") Integer id, Model model) {
		Tmio1Servicio servicio = servicioService.buscarPorId(id);
		if (servicio == null)
			throw new IllegalArgumentException("Invalid service Id:" + id);
		model.addAttribute("servicio", servicio);
		model.addAttribute("conductores", conductores);
		model.addAttribute("buses", buses);
		model.addAttribute("rutas", rutas);
		return "servicios/update-servicio";
	}
	
	@PostMapping("/servicios/edit/{idHash}")
	public String updateService(@PathVariable("idHash") Integer id,
			@RequestParam(value = "action", required = true) String action, @Valid @ModelAttribute Tmio1ServicioPK tmio1ServicioPK,
			BindingResult bindingResult, Model model) throws Exception {
		if (action != null && !action.equals("Cancel")) {
			Tmio1Servicio servicio = servicioService.buscarPorId(id);
			if (servicio == null)
				throw new IllegalArgumentException("Invalid service Id:" + id);
			model.addAttribute("servicio", servicio);
			if (bindingResult.hasErrors()) {
				model.addAttribute("conductores", conductores);
				model.addAttribute("buses", buses);
				model.addAttribute("rutas", rutas);
				return "servicios/update-servicio";
			}else {
				Tmio1Servicio ser = servicioService.buscarPorId(id);
				servicioService.delete(ser);
				Tmio1Servicio s = new Tmio1Servicio();
				s.setId(tmio1ServicioPK);
				s.setTmio1Conductore(conductorService.findById(tmio1ServicioPK.getCedulaConductor()));
				s.setTmio1Bus(busService.findById(tmio1ServicioPK.getIdBus()));
				s.setTmio1Ruta(rutaService.findById(tmio1ServicioPK.getIdRuta()));
				s.setIdHash(s.getId().hashCode());
				servicioService.save(s);
			}
		}
		return "redirect:/servicios/";
	}
	
	@PostMapping("/servicios/search/")
	public String searchServicio(Tmio1Servicio servicio, Model model) {
		try {
			Iterable<Tmio1Servicio> todos = servicioService.findAll();
			for(Tmio1Servicio ser : todos) {
				if(ser.getId().getFechaInicio().equals(servicio.getId().getFechaInicio())) {
					model.addAttribute("servicios", ser);
				}
			}
		} catch(Exception s) {
			model.addAttribute("servicios", servicioService.findAll());
		}
		model.addAttribute("servicioSearched" , new Tmio1Servicio());
		return "servicios/index";
	}


}
