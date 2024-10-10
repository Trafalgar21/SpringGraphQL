package com.graphQL_demo.demo.Repository;

import com.graphQL_demo.demo.Model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
