package com.vsvdev.MultiDB;


import com.vsvdev.MultiDB.book.repository.BookRepository;
import com.vsvdev.MultiDB.model.book.Book;
import com.vsvdev.MultiDB.model.user.User;
import com.vsvdev.MultiDB.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@SpringBootApplication
//@EnableTransactionManagement
//@EnableMongoRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Book.class))
//@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Book.class))
@RestController
public class MultiDbApplication {

	private final UserRepository userRepository;

	private final BookRepository bookRepository;
@Autowired
	public MultiDbApplication(UserRepository userRepository, BookRepository bookRepository) {
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}

	@PostConstruct
	public void addDataToDatabases(){
	userRepository.saveAll( Stream.of(new User(1,"Sam"),
			new User( 2,"Ann" ),new User( 3,"Tom" ) ).collect( Collectors.toList()) );
	bookRepository.saveAll( Stream.of( new Book( 1,"Java" ),new Book( 2,"Spring" ),
			new Book( 3,"Hibernate" )).collect( Collectors.toList()) );
	}


	@GetMapping("/getUsers")
	public List<User> getUsers(){
	return userRepository.findAll();
	}

	@GetMapping("/getBooks")
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}


	public static void main(String[] args) {
		SpringApplication.run(MultiDbApplication.class, args);
	}

}
