package com.anderson.testing_docker.core.user.entrypoint.controller.exception;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.anderson.testing_docker.core.user.builders.UserBuilderTest.newUserDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class GenericExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserRepository repository;

    @Test
    @DisplayName("Should return 500 Internal Server Error when a generic exception occurs during user registration")
    void registerWithGenericException() throws Exception {
        doThrow(new RuntimeException("Generic Exception")).when(repository).save(any(User.class));

        UserDTO dto = newUserDTO();
        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }
}
