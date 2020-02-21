package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	//JdbcTemplate jdbcTemplate;
	//EntityManager manager;
	LocationRepository locationRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	//@Transactional
	public void run(String... args) throws Exception {
		/*jdbcTemplate.execute("CREATE TABLE locations(id int, lat double, lon double)");
		jdbcTemplate.update("INSERT INTO locations values(0, 60.0, 60.0)");
		jdbcTemplate.update("INSERT INTO locations values(2, 65.0, 68.0)");

		List<Location> locations = jdbcTemplate.query("SELECT * FROM locations",
				(result, rowNum) -> {
					Location loc = new Location(result.getInt("id"),
							result.getDouble("lat"),
							result.getDouble("lon"));
					return loc;
				});

		locations.forEach(System.out::println);
		*/

		//manager.persist(new Location(5,5));
		//Location obj = manager.find(Location.class,1);
		//System.out.println(obj);


		List<Location> locations = new ArrayList();
		locations.add(new Location(5, 5));
		locations.add(new Location(5, 10));
		locations.add(new Location(5, 2));
		locations.add(new Location(6, 2));
		locationRepository.saveAll(locations);
		locationRepository.findByLatitudeOrderByLongitudeDesc(5).forEach(System.out::println);

		//locationRepository.save(new Location(66,66));
		//Optional<Location> loc = locationRepository.findById(1);
		//loc.ifPresent(System.out::println);
	}
}
