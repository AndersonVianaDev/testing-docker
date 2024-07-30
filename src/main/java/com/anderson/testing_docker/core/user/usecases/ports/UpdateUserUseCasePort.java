package com.anderson.testing_docker.core.user.usecases.ports;

import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;

import java.util.UUID;

public interface UpdateUserUseCasePort {
    User execute(UUID id, UserDTO dto);
}
