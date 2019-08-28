package com.github.pedrobacchini.springhateoas.controller;

import com.github.pedrobacchini.springhateoas.domain.User;
import com.github.pedrobacchini.springhateoas.mapper.UserResourceMapper;
import com.github.pedrobacchini.springhateoas.repository.UserRepository;
import com.github.pedrobacchini.springhateoas.resource.UserResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@ExposesResourceFor(User.class)
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

    private final UserRepository userRepository;
    private final UserResourceMapper mapper;

    public UserController(UserRepository userRepository, UserResourceMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Collection<UserResource>> findAll() {
        List<User> Users = userRepository.findAll();
        Collection<UserResource> resources = mapper.toResource(Users);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> findById(@PathVariable Long id) {
        final Optional<User> found = userRepository.findById(id);
        if(found.isPresent()) {
            return ResponseEntity.ok(mapper.toResource(found.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserResource> create(@RequestBody User User) {
        final User createdUser = userRepository.save(User);
        UserResource resource = mapper.toResource(createdUser);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserResource> update(@PathVariable Long id, @RequestBody User toUpdate) {
        final Optional<User> found = userRepository.findById(id);
        if(found.isPresent()) {
            User updated = userRepository.save(toUpdate);
            return ResponseEntity.ok(mapper.toResource(updated));
        }
        return ResponseEntity.notFound().build();
    }

}
