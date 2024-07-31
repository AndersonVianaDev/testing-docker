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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindUserByIdTest {

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
    @DisplayName("Should return 200 OK when fetching a registered user by ID successfully")
    void findByIdSuccessfully() throws Exception {
        User user = repository.save(newUser());
        UUID id = user.getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        User userResult = mapper.readValue(content, User.class);

        assertEquals(user, userResult);
    }

    @Test
    @DisplayName("Should return 404 Not Found when fetching a non-existent user by ID")
    void findByIdWithNotFound() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.get("/users/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
