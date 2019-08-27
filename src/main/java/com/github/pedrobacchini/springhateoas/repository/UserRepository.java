package com.github.pedrobacchini.springhateoas.repository;

import com.github.pedrobacchini.springhateoas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
