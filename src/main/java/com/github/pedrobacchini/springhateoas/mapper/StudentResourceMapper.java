package com.github.pedrobacchini.springhateoas.mapper;

import com.github.pedrobacchini.springhateoas.domain.Student;
import com.github.pedrobacchini.springhateoas.resource.StudentResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class StudentResourceMapper {

    private final EntityLinks entityLinks;
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    public StudentResourceMapper(EntityLinks entityLinks) { this.entityLinks = entityLinks; }

    public StudentResource toResource(Student student) {
        StudentResource resource = new StudentResource(student.getId(), student.getName());
        final Link selfLink = entityLinks.linkToSingleResource(student);
        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE));
        resource.add(selfLink.withRel(DELETE));
        return resource;
    }

    public Collection<StudentResource> toResource(Collection<Student> domainObjects) {
        return domainObjects.stream().map(this::toResource).collect(Collectors.toList());
    }
}
