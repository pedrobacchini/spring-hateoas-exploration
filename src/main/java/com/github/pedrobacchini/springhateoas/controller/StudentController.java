package com.github.pedrobacchini.springhateoas.controller;

import com.github.pedrobacchini.springhateoas.domain.Student;
import com.github.pedrobacchini.springhateoas.mapper.StudentResourceMapper;
import com.github.pedrobacchini.springhateoas.repository.StudentRepository;
import com.github.pedrobacchini.springhateoas.resource.StudentResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@ExposesResourceFor(Student.class)
@RequestMapping(value = "/student", produces = "application/json")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentResourceMapper mapper;

    public StudentController(StudentRepository studentRepository, StudentResourceMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Collection<StudentResource>> findAll() {
        List<Student> students = studentRepository.findAll();
        Collection<StudentResource> resources = mapper.toResource(students);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResource> findById(@PathVariable Long id) {
        final Optional<Student> found = studentRepository.findById(id);
        if(found.isPresent()) {
            return ResponseEntity.ok(mapper.toResource(found.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentResource> create(@RequestBody Student student) {
        final Student createdStudent = studentRepository.save(student);
        StudentResource resource = mapper.toResource(createdStudent);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentResource> update(@PathVariable Long id, @RequestBody Student toUpdate) {
        final Optional<Student> found = studentRepository.findById(id);
        if(found.isPresent()) {
            Student updated = studentRepository.save(toUpdate);
            return ResponseEntity.ok(mapper.toResource(updated));
        }
        return ResponseEntity.notFound().build();
    }

}
