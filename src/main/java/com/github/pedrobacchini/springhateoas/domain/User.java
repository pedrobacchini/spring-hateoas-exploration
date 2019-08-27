package com.github.pedrobacchini.springhateoas.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE) // For Hibernate
public class User implements Identifiable<Long>, Serializable {

    private static final long serialVersionUID = -2858602168333720917L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
