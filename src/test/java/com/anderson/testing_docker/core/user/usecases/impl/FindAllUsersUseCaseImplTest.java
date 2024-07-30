package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUserList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllUsersUseCaseImplTest {

    @InjectMocks
    private FindAllUsersUseCaseImpl useCase;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("find all users successfully")
    void executeSuccessfully() {
        List<User> users = newUserList();

        when(repository.findAll()).thenReturn(users);

        List<User> result = useCase.execute();

        assertEquals(users, result);
    }
}