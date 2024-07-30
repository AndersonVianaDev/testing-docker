package com.anderson.testing_docker.core.user.usecases.ports;

import com.anderson.testing_docker.core.user.domain.User;

import java.util.UUID;

public interface FindUserByIdUseCasePort {
    User execute(UUID id);
}
