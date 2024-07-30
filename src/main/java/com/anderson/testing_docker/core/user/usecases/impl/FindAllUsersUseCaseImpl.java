package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.usecases.ports.FindAllUsersUseCasePort;

import java.util.List;

public class FindAllUsersUseCaseImpl implements FindAllUsersUseCasePort {

    private final UserRepository repository;

    public FindAllUsersUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> execute() {
        return repository.findAll();
    }
}
