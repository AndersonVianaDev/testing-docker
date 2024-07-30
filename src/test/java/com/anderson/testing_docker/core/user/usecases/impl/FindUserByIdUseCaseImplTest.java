package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.exceptions.NotFoundException;
import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.testing_docker.core.exceptions.constants.ExceptionConstants.USER_NOT_FOUND;
import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserByIdUseCaseImplTest {

    @InjectMocks
    private FindUserByIdUseCaseImpl useCase;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("find user by id successfully")
    void executeSuccessfully() {
        User user = newUser();
        UUID id = user.getId();

        when(repository.findById(id)).thenReturn(Optional.of(user));

        User result = useCase.execute(id);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("User not found")
    void executeWithNotFoundException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> useCase.execute(id));

        assertEquals(USER_NOT_FOUND, exception.getMessage());
    }
}