package com.graphQL_demo.demo.Resolver;

import com.graphQL_demo.demo.Exception.ActorNotFoundException;
import com.graphQL_demo.demo.Exception.MovieNotFoundException;
import com.graphQL_demo.demo.Model.Actor;
import com.graphQL_demo.demo.Model.Movie;
import com.graphQL_demo.demo.Repository.ActorRepository;
import com.graphQL_demo.demo.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@Slf4j
public class MovieResolver {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @QueryMapping
    public List<Movie> getAllMovies (){
        return movieRepository.findAll();
    }

    @QueryMapping
    public Optional<Movie> getMovieById(@Argument Integer id) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(Long.valueOf(id));
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie Not Found");
        }
        return movieOptional;
    }

    @MutationMapping
    Movie addMovie(@Argument(name = "movie") MovieInput movieInput) throws ActorNotFoundException {

        try {
            Movie movie = new Movie();
            movie.setName(movieInput.name);
            movie.setRatings(movieInput.ratings);
            movie.setActorId(movieInput.actorId);

            Optional<Actor> actor = actor(movie);

            return movieRepository.save(movie);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        return null;
    }

    @MutationMapping
    Actor addActor(@Argument String name){
        Actor actor = new Actor();
        actor.setActor_name(name);
        return actorRepository.save(actor);
    }

    @MutationMapping
    Actor modifyActor(@Argument Integer id, @Argument String name) throws ActorNotFoundException {
        Optional<Actor> optionalActor = actorRepository.findById(Long.valueOf(id));
        if (optionalActor.isEmpty())
            throw new ActorNotFoundException("Actor is not avialable");

        Actor actor = optionalActor.get();
        actor.setActor_name(name);
        return actorRepository.save(actor);
    }

    //method name should be the same with the instance on graphQL structure (schema.graphqls)
    @SchemaMapping
    public Optional<Actor> actor(Movie movie) throws ActorNotFoundException {
        Optional<Actor> actor = actorRepository.findById(Long.valueOf(movie.getActorId()));

        if (actor.isEmpty()){
            throw new ActorNotFoundException("Actor not found");
        }

        return actor;
    }

    record MovieInput(String name, Integer ratings, Integer actorId){}
}
