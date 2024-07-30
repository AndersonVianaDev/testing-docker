package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.exceptions.DataConflictException;
import com.anderson.testing_docker.core.user.builder.UserBuilder;
import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;
import com.anderson.testing_docker.core.user.usecases.ports.RegisterUserUseCasePort;

import static com.anderson.testing_docker.core.exceptions.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;

public class RegisterUserUseCaseImpl implements RegisterUserUseCasePort {

    private final UserRepository repository;

    public RegisterUserUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(UserDTO dto) {
        if(repository.findByEmail(dto.email()).isPresent()) throw new DataConflictException(EMAIL_ALREADY_REGISTERED);

        User user = new UserBuilder()
                .withName(dto.name())
                .withEmail(dto.email())
                .withDocument(dto.document())
                .build();

        return repository.save(user);
    }
}
