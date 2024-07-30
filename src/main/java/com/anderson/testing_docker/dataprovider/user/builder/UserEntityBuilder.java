package com.anderson.testing_docker.dataprovider.user.builder;

import com.anderson.testing_docker.dataprovider.user.entity.UserEntity;

import java.util.UUID;

public class UserEntityBuilder {
    private UUID id;
    private String name;
    private String email;
    private String document;

    public UserEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public UserEntityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserEntityBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntityBuilder withDocument(String document) {
        this.document = document;
        return this;
    }

    public UserEntity build() {
        return new UserEntity(id, name, email, document);
    }
}
