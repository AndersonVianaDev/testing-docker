package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.usecases.ports.FindUserByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {

    @InjectMocks
    private DeleteUserUseCaseImpl useCase;

    @Mock
    private UserRepository repository;

    @Mock
    private FindUserByIdUseCasePort findUserById;

    @Test
    @DisplayName("delete user successfully")
    void deleteSuccessfully() {
        User user = newUser();
        UUID id = user.getId();

        when(findUserById.execute(id)).thenReturn(user);

        useCase.delete(id);

        verify(repository, times(1)).delete(id);
    }
}