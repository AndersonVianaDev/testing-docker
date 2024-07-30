package com.anderson.testing_docker.core.user.usecases.ports;

import com.anderson.testing_docker.core.user.domain.User;

import java.util.List;

public interface FindAllUsersUseCasePort {
    List<User> execute();
}
