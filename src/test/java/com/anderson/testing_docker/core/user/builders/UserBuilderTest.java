package com.anderson.testing_docker.core.user.builders;

import com.anderson.testing_docker.core.user.builder.UserBuilder;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;
import com.anderson.testing_docker.dataprovider.user.entity.UserEntity;

import java.util.List;
import java.util.UUID;

import static com.anderson.testing_docker.dataprovider.user.mapper.UserEntityMapper.toUserEntity;

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

    public static User newUser2() {
            return new UserBuilder()
                    .withId(UUID.randomUUID())
                    .withName("test2")
                    .withEmail("test2@gmail.com")
                    .withDocument("29345645643")
                    .build();
    }

    public static User newUser3() {
        return new UserBuilder()
                .withId(UUID.randomUUID())
                .withName("test3")
                .withEmail("test3@gmail.com")
                .withDocument("39345645643")
                .build();
    }

    public static List<User> newUserList() {
        return List.of(newUser(), newUser2(), newUser3());
    }

    public static List<UserEntity> newUserEntityList() {
        return List.of(toUserEntity(newUser()), toUserEntity(newUser2()), toUserEntity(newUser3()));
    }
}
