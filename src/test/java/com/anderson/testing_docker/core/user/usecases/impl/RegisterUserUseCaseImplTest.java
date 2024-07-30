package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.exceptions.DataConflictException;
import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.anderson.testing_docker.core.exceptions.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;
import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUser;
import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUserDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseImplTest {

    @InjectMocks
    private RegisterUserUseCaseImpl useCase;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("Register user successfully")
    void executeSuccessfully() {
        UserDTO dto = newUserDTO();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(repository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = useCase.execute(dto);

        assertEquals(dto.name(), result.getName());
        assertEquals(dto.email(), result.getEmail());
        assertEquals(dto.document(), result.getDocument());
    }

    @Test
    @DisplayName("email already registered in another account")
    void executeWithDataConflictException() {
        UserDTO dto = newUserDTO();
        User user = newUser();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));

        DataConflictException exception = assertThrows(DataConflictException.class, () -> useCase.execute(dto));

        assertEquals(EMAIL_ALREADY_REGISTERED, exception.getMessage());
    }
}