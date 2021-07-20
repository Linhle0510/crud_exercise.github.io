package com.crud.demo.web;

import java.util.List;
import java.util.Optional;

import com.crud.demo.exception.RecordNotFoundException;
import com.crud.demo.model.PersonEntity;
import com.crud.demo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PersonController {
	@Autowired
	PersonService service;

	@RequestMapping
	public String getAllPeople(Model model) {
		List<PersonEntity> list = service.getAllPeople();

		model.addAttribute("people", list);
		return "list-people";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editPersonById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			PersonEntity entity = service.getPersonById(id.get());
			model.addAttribute("person", entity);
		} else {
			model.addAttribute("person", new PersonEntity());
		}
		return "add-edit-person";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deletePersonById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deletePersonById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createPerson", method = RequestMethod.POST)
	public String createOrUpdatePerson(PersonEntity person) {
		service.createOrUpdatePerson(person);
		return "redirect:/";
	}

	@RequestMapping(path = { "/search", "/search/{id}" })
	public String searchPersonById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.getPersonById(id);
		
		return "redirect:/";
	}

}
