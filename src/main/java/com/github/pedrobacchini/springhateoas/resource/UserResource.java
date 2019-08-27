package com.github.pedrobacchini.springhateoas.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private final Long id;
    @Getter
    private final String username;
    @Getter
    private final String password;

    public UserResource(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @JsonProperty("id")
    public Long getResourceId() { return id; }
}
