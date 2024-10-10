package com.graphQL_demo.demo;

import com.graphQL_demo.demo.Model.Actor;
import com.graphQL_demo.demo.Model.Movie;
import com.graphQL_demo.demo.Repository.ActorRepository;
import com.graphQL_demo.demo.Repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	//Insert data into database
	//Create a schema of "graphQL" in a database with port "5433"
	@Bean
	CommandLineRunner run(MovieRepository movieRepository, ActorRepository actorRepository){
		return args -> {
			Actor actor = new Actor();
			actor.setActor_name("Thomas Oviedo");

			Movie movie1 = new Movie();
			movie1.setName("Matrix");
			movie1.setRatings(5);
			movie1.setActorId(1);

			Movie movie2 = new Movie();
			movie2.setName("Now you see me 2");
			movie2.setRatings(10);
			movie2.setActorId(1);


			actorRepository.save(actor);

			movieRepository.save(movie1);
			movieRepository.save(movie2);

		};
	}

}
