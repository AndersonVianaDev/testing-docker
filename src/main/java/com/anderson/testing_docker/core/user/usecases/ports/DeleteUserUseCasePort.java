package com.anderson.testing_docker.core.user.usecases.ports;

import java.util.UUID;

public interface DeleteUserUseCasePort {
    void delete(UUID id);
}
