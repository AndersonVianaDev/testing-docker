package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.usecases.ports.DeleteUserUseCasePort;
import com.anderson.testing_docker.core.user.usecases.ports.FindUserByIdUseCasePort;

import java.util.UUID;

public class DeleteUserUseCaseImpl implements DeleteUserUseCasePort {

    private final UserRepository repository;

    private final FindUserByIdUseCasePort findUserById;

    public DeleteUserUseCaseImpl(UserRepository repository, FindUserByIdUseCasePort findUserById) {
        this.repository = repository;
        this.findUserById = findUserById;
    }

    @Override
    public void delete(UUID id) {
        findUserById.execute(id);
        repository.delete(id);
    }
}
