package com.anderson.testing_docker.dataprovider.user.mapper;

import com.anderson.testing_docker.core.user.builder.UserBuilder;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.dataprovider.user.builder.UserEntityBuilder;
import com.anderson.testing_docker.dataprovider.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public class UserEntityMapper {
    public static UserEntity toUserEntity(User user) {
        return new UserEntityBuilder()
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withDocument(user.getDocument())
                .build();
    }

    public static User toUser(UserEntity userEntity) {
        return new UserBuilder()
                .withId(userEntity.getId())
                .withName(userEntity.getName())
                .withEmail(userEntity.getEmail())
                .withDocument(userEntity.getDocument())
                .build();
    }

    public static Optional<User> toOptionalUser(Optional<UserEntity> userEntity) {
        return userEntity.map(UserEntityMapper::toUser);
    }

    public static List<User> toUsers(List<UserEntity> userEntities) {
        return userEntities.stream().map(UserEntityMapper::toUser).toList();
    }
}
