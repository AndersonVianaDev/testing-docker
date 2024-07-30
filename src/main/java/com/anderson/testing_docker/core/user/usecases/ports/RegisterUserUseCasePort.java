package com.anderson.testing_docker.core.user.usecases.ports;

import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;

public interface RegisterUserUseCasePort {
    User execute(UserDTO dto);
}
