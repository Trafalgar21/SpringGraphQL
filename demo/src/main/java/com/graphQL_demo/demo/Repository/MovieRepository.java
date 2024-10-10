package com.graphQL_demo.demo.Repository;

import com.graphQL_demo.demo.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
