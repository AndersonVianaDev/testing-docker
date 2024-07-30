package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;
import com.anderson.testing_docker.core.user.usecases.ports.FindUserByIdUseCasePort;
import com.anderson.testing_docker.core.user.usecases.ports.UpdateUserUseCasePort;


import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.nonNull;

public class UpdateUserUseCaseImpl implements UpdateUserUseCasePort {

    private final UserRepository repository;

    private final FindUserByIdUseCasePort findUserById;

    public UpdateUserUseCaseImpl(UserRepository repository, FindUserByIdUseCasePort findUserById) {
        this.repository = repository;
        this.findUserById = findUserById;
    }

    @Override
    public User execute(UUID id, UserDTO dto) {
        User user = findUserById.execute(id);

        if(nonNull(dto.name()) && !Objects.equals(dto.name(), user.getName())) {
            user.setName(dto.name());
        }
        if(nonNull(dto.email()) && !Objects.equals(dto.email(), user.getEmail())) {
            user.setEmail(dto.email());
        }
        if(nonNull(dto.document()) && !Objects.equals(dto.document(), user.getDocument())) {
            user.setDocument(dto.document());
        }

        return repository.update(user);
    }
}
