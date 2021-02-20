package spring.boot.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import spring.boot.webflux.model.Todo;
import spring.boot.webflux.repository.TodoRepository;

@Component
public class PopulateData implements CommandLineRunner {

	@Autowired
	TodoRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Verifying Resources");
		for (int i = 1; i <= 5; i++) {
			if ( i % 2 == 0 ) {
			Todo todo = new Todo(null, "This is my todo" + i, false);
			System.out.println("Inserting new todo with status false ... " + todo.toString());
			repository.save(todo).subscribe();
			} else {
				Todo todo = new Todo(null, "This is my todo" + i, true);
				System.out.println("Inserting new todo with status true ... " + todo.toString());
				repository.save(todo).subscribe();
			}
			
			
		}
		
	}
}
