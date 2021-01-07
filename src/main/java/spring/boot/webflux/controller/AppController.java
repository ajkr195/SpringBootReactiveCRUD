package spring.boot.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.boot.webflux.repository.TodoRepository;

@Controller
public class AppController {
	
	@Autowired
	TodoRepository repository;
	
	@RequestMapping("/")
	public String crud(final Model model) {
		model.addAttribute("todos", repository.findAll());
		return "crud";
	}
	
}
