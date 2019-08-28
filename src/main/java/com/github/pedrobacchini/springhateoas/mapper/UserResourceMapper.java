package com.github.pedrobacchini.springhateoas.mapper;

import com.github.pedrobacchini.springhateoas.domain.User;
import com.github.pedrobacchini.springhateoas.resource.UserResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserResourceMapper {

    private final EntityLinks entityLinks;
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    public UserResourceMapper(EntityLinks entityLinks) { this.entityLinks = entityLinks; }

    public UserResource toResource(User user) {
        UserResource resource = new UserResource(user.getId(), user.getUsername(), user.getPassword());
        final Link selfLink = entityLinks.linkToSingleResource(user);
        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE));
        resource.add(selfLink.withRel(DELETE));
        return resource;
    }

    public Collection<UserResource> toResource(Collection<User> domainObjects) {
        return domainObjects.stream().map(this::toResource).collect(Collectors.toList());
    }
}
