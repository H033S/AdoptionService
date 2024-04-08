package com.expeditors.adoption;

import com.expeditors.adoption.domain.entities.*;
import com.expeditors.adoption.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;

@SpringBootApplication
public class AdoptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionApplication.class, args);
	}

}

@Component
class  RunApp implements CommandLineRunner{

	private final AdoptionService adoptionService;

	public RunApp(AdoptionService adoptionService) {
		this.adoptionService = adoptionService;
	}

	@Override
	public void run(String... args) throws Exception {
		var adopter1 = new Adopter(0, "Antonio Nazco", "123-123-4567");
		var adopter2 = new Adopter(0, "Nathaly Nazco", "123-123-4567");

		var pet1 = new Pet(0, PetBreed.POODLE, PetType.DOG,  "Suki" );
		var pet2 = new Pet(0, PetBreed.SIAMESE, PetType.CAT,  "Bing" );
		var pet3 = new Pet(0, PetBreed.SIAMESE, PetType.CAT,  "Bang" );

		var ld1 = LocalDate.now().minusDays(3);
		var ld2 = LocalDate.now().minusDays(4);
		var ld3 = LocalDate.now().minusDays(5);

		adoptionService.addNewAdoption( new Adoption(0, adopter1, pet1, ld1));
		adoptionService.addNewAdoption( new Adoption(0, adopter2, pet2, ld2));
		adoptionService.addNewAdoption( new Adoption(0, adopter2, pet3, ld3));

		var adoptions  = adoptionService.findAllAdoptions();
		adoptions.forEach(System.out::println);
	}
}