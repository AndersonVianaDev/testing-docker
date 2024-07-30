package com.anderson.testing_docker.core.user.usecases.impl;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;
import com.anderson.testing_docker.core.user.usecases.ports.FindUserByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUser;
import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUserUpdateDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {

    @InjectMocks
    private UpdateUserUseCaseImpl useCase;

    @Mock
    private UserRepository repository;

    @Mock
    private FindUserByIdUseCasePort findUserById;

    @Test
    @DisplayName("update user successfully")
    void executeSuccessfully() {
        User user = newUser();
        UUID id = user.getId();

        UserDTO userDTO = newUserUpdateDTO();

        when(findUserById.execute(id)).thenReturn(user);
        when(repository.update(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = useCase.execute(id, userDTO);

        assertEquals(userDTO.name(), result.getName());
        assertEquals(userDTO.document(), result.getDocument());
        assertEquals(userDTO.email(), result.getEmail());
    }
}