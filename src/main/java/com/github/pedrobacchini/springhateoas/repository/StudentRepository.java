package com.github.pedrobacchini.springhateoas.repository;

import com.github.pedrobacchini.springhateoas.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
