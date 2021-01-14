package spring.boot.webflux.controller;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import spring.boot.webflux.model.Todo;
import spring.boot.webflux.repository.TodoRepository;

@Controller
public class AppController {

	@Autowired
	TodoRepository repository;

//	@PostConstruct
//	private void init() {
//		System.out.println("Verifying Resources");
//		for (int i = 0; i < 999; i++) {
//			Todo todo = new Todo(null, "todo" + i, false);
//			System.out.println("Inserting new todo ... " + todo.toString());
//			repository.save(todo);
//		}
//	}

	@RequestMapping("/crud")
	public String crud(final Model model) {
		model.addAttribute("todos", repository.findAll());
		return "crud";
	}

	@RequestMapping("/")
	public String index(final Model model) {
		IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(
				repository.findAll().log().delayElements(Duration.ofMillis(300)), 2);
//		IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(
//				repository.findAll());
		model.addAttribute("todos", reactiveDataDrivenMode);
		return "crud";

	}
}
