package com.github.pedrobacchini.springhateoas.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class StudentResource extends ResourceSupport {

    private final Long id;
    @Getter
    private final String name;

    public StudentResource(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @JsonProperty("id")
    public Long getResourceId() { return id; }
}
