package com.aziz.voyages.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aziz.voyages.entities.voyage;
import com.aziz.voyages.service.voyageService;

@Controller
public class voyageController {
	@Autowired
	voyageService voyageService;

	@RequestMapping("/Listevoyages")
	public String listevoyages(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "3") int size)
{
		Page<voyage> voys = voyageService.getAllvoyagesParPage(page, size);
		modelMap.addAttribute("voyages", voys);
		 modelMap.addAttribute("pages", new int[voys.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listevoyages";
	}

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createvoyage";
	}

	@RequestMapping("/savevoyage")
	public String savevoyage(@ModelAttribute("produit") voyage voyage, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voyage.setDateCreation(dateCreation);

		voyage savevoyage = voyageService.savevoyage(voyage);
		String msg = "produit enregistré avec Id " + savevoyage.getIdvoyage();
		modelMap.addAttribute("msg", msg);
		return "createvoyage";
	}

	@RequestMapping("/supprimervoyage")
	public String supprimervoyage(@RequestParam("id") Long id,
			ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size)
	{
		voyageService.deletevoyageById(id);
		Page<voyage> voys = voyageService.getAllvoyagesParPage(page, size);
		modelMap.addAttribute("voyages", voys);
		modelMap.addAttribute("pages", new int[voys.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listevoyages";
	}

	@RequestMapping("/modifiervoyage")
	public String editervoyage(@RequestParam("id") Long id, ModelMap modelMap) {
		voyage v = voyageService.getvoyage(id);
		modelMap.addAttribute("produit", v);
		return "editervoyage";
	}

	@RequestMapping("/updatevoyage")
	public String updatevoyage(@ModelAttribute("voyage") voyage voyage, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voyage.setDateCreation(dateCreation);

		voyageService.updatevoyage(voyage);
		List<voyage> voys = voyageService.getAllvoyages();
		modelMap.addAttribute("voyages", voys);
		return "listevoyages";
	}
}