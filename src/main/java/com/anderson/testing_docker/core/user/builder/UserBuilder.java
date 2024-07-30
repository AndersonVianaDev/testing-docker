package com.anderson.testing_docker.core.user.builder;

import com.anderson.testing_docker.core.user.domain.User;

import java.util.UUID;

public class UserBuilder {
    private UUID id;
    private String name;
    private String email;
    private String document;

    public UserBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withDocument(String document) {
        this.document = document;
        return this;
    }

    public User build() {
        return new User(id, name, email, document);
    }
}
