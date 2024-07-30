package com.anderson.testing_docker.core.user.builders;

import com.anderson.testing_docker.core.user.builder.UserBuilder;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;

import java.util.List;
import java.util.UUID;

public class UserBuilderTest {
    public static UserDTO newUserDTO() {
        return new UserDTO("test1", "test1@gmail.com", "19345645643");
    }

    public static UserDTO newUserUpdateDTO() {
        return new UserDTO("test2", "test2@gmail.com", "29345645643");
    }

    public static User newUser() {
        return new UserBuilder()
                .withId(UUID.randomUUID())
                .withName("test1")
                .withEmail("test1@gmail.com")
                .withDocument("19345645643")
                .build();
    }

    public static List<User> newUserList() {
        return List.of(newUser(), newUser(), newUser());
    }
}
