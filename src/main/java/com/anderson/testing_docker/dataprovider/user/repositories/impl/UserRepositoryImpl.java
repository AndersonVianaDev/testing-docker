package com.anderson.testing_docker.dataprovider.user.repositories.impl;

import com.anderson.testing_docker.core.exceptions.NotFoundException;
import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.dataprovider.user.entity.UserEntity;
import com.anderson.testing_docker.dataprovider.user.repositories.port.SpringUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.anderson.testing_docker.core.exceptions.constants.ExceptionConstants.USER_NOT_FOUND;
import static com.anderson.testing_docker.dataprovider.user.mapper.UserEntityMapper.*;

public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SpringUserRepository repository;

    @Override
    public User save(User user) {
        UserEntity userEntity = toUserEntity(user);
        return toUser(repository.save(userEntity));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return toOptionalUser(repository.findById(id));
    }

    @Override
    public List<User> findAll() {
        return toUsers(repository.findAll());
    }

    @Override
    public void delete(UUID id) {
        UserEntity userEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        repository.delete(userEntity);
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = repository.findById(user.getId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setEmail(user.getEmail());
        return toUser(repository.save(userEntity));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return toOptionalUser(repository.findByEmail(email));
    }
}
