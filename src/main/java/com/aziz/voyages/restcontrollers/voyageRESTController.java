package com.aziz.voyages.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aziz.voyages.entities.voyage;
import com.aziz.voyages.service.voyageService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class voyageRESTController {
	@Autowired
	voyageService VoyageService;

	@RequestMapping(method = RequestMethod.GET)
	public List<voyage> getAllvoyages() {
		return VoyageService.getAllvoyages();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public voyage getvoyageById(@PathVariable("id") Long id) {
		return VoyageService.getvoyage(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public voyage createvoyage(@RequestBody voyage voyage) {
		return VoyageService.savevoyage(voyage);
	}
	@RequestMapping(method = RequestMethod.PUT)
	public voyage updatevoyage(@RequestBody voyage voyage) {
		return VoyageService.updatevoyage(voyage);
	}
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deletevoyage(@PathVariable("id") Long id)
	{
	VoyageService.deletevoyageById(id);
	}
	@RequestMapping(value="/prodscat/{idCat}",method = RequestMethod.GET)
	public List<voyage> getvoyagesByCatId(@PathVariable("idCat") Long idCat) {
	return VoyageService.findByCategorieIdCat(idCat);
	}

	
}
