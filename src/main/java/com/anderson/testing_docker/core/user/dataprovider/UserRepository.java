package com.anderson.testing_docker.core.user.dataprovider;

import com.anderson.testing_docker.core.user.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    void delete(UUID id);
    User update(User user);
    Optional<User> findByEmail(String email);
}
