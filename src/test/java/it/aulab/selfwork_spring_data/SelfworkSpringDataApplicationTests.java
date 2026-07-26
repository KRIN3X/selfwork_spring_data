package it.aulab.selfwork_spring_data;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import it.aulab.selfwork_spring_data.models.Author;
import it.aulab.selfwork_spring_data.repositories.AuthorRepository;

// @SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SelfworkSpringDataApplicationTests {

	@Autowired
	AuthorRepository authorRepository;

	@BeforeEach
	void load(){
		Author a1 = new Author();
		a1.setName("Giuseppe");
		a1.setSurname("Verdi");
		a1.setEmail("VerdiG@test.it");
		authorRepository.save(a1);
	}

	@Test
	void contextLoads() {

	}

	@Test
	void findByName(){
		assertThat(authorRepository.findByName("Giuseppe")).extracting("name").containsOnly("Giuseppe");
	}

	@Test
	void sameNameAuthor(){
		assertThat(authorRepository.authorsWithSameName()).extracting("name").containsOnly("Giuseppe");
	}

}
