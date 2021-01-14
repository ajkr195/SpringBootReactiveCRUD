package spring.boot.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;
import spring.boot.webflux.model.Todo;
import spring.boot.webflux.repository.TodoRepository;

@SpringBootApplication
public class Application { // implements CommandLineRunner {

//	@Autowired
//	TodoRepository repository;

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		// populator.addPopulators(new ResourceDatabasePopulator(new
		// ClassPathResource("data.sql")));
		initializer.setDatabasePopulator(populator);
		return initializer;
	}

//	@Override
//    public void run(String... args) throws Exception {
//        // TODO Auto-generated method stub
//        System.out.println("Verifying Resources");
//        for (int i = 0; i < 3; i++) {
//            Todo todo = new Todo(null, "todo" + i, false);
//            System.out.println("Inserting new todo ... " + todo.toString());
//            repository.save(todo).subscribe();
//        }
//    }

}