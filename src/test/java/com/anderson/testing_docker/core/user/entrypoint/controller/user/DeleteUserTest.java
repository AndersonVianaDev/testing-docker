package com.anderson.testing_docker.core.user.entrypoint.controller.user;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.dataprovider.user.repositories.port.SpringUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUser;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DeleteUserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private SpringUserRepository springRepository;

    @BeforeEach
    void setup() {
        springRepository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        springRepository.deleteAll();
    }

    @Test
    @DisplayName("Should return 204 No Content when deleting a user successfully")
    void deleteSuccessfully() throws Exception {
        User user = repository.save(newUser());
        UUID id = user.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Optional<User> userDeleted = repository.findById(id);
        assertFalse(userDeleted.isPresent(), "User should be deleted");
    }
}
