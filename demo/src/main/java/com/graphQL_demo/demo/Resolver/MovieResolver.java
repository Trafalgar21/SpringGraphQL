package com.graphQL_demo.demo.Resolver;

import com.graphQL_demo.demo.Model.Actor;
import com.graphQL_demo.demo.Model.Movie;
import com.graphQL_demo.demo.Repository.ActorRepository;
import com.graphQL_demo.demo.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
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
    public Optional<Movie> getMovieById(@Argument Integer id){
        return movieRepository.findById(Long.valueOf(id));
    }

    //method name should be the same with the instance on graphQL structure (schema.graphqls)
    @SchemaMapping
    public Optional<Actor> actor(Movie movie){
        return actorRepository.findById(Long.valueOf(movie.getActorId()));
    }

}
