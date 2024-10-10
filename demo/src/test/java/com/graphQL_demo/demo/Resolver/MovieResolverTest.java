package com.graphQL_demo.demo.Resolver;

import com.graphQL_demo.demo.Model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

@GraphQlTest(MovieResolver.class)
class MovieResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void getMovies(){
        graphQlTester.documentName("movies")
                .execute()
                .path("getAllMovies")
                .entityList(Movie.class)
                .hasSize(2);
    }

}