package com.anderson.testing_docker.core.user.entrypoint.controller.user;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.dataprovider.user.entity.UserEntity;
import com.anderson.testing_docker.dataprovider.user.repositories.port.SpringUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.util.List;

import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUserEntityList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindAllUserTest {

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
    @DisplayName("Should return 200 OK and a list of users when fetching all users")
    void findAll() throws Exception {
        List<UserEntity> userEntities = springRepository.saveAll(newUserEntityList());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        List<User> users = mapper.readValue(content, new TypeReference<List<User>>() {});

        assertEquals(userEntities.size(), users.size());
    }
}
