package com.example.accountant.integration.controller;

import com.example.accountant.dto.UserCreateUpdateDto;
import com.example.accountant.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Sql({"classpath:sql/data.sql"})
@ActiveProfiles("test")
@Transactional
public class UserControllerTest {

    private final MockMvc mockMvc;
    private final UserService userService;

    private final static int USER_ID = 10;
    private final static int SIZE_ARRAY = 6;

    @Test
    @SneakyThrows
    void findAll() {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(SIZE_ARRAY))
                .andExpect(jsonPath("$[0].id").value(USER_ID))
                .andExpect(jsonPath("$[0].firstname").value("Homer"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    void findById() {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(USER_ID))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    void create() {
        String newUser = new ObjectMapper()
                .writeValueAsString(new UserCreateUpdateDto("Ben Kenobi", null));
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUser)
        ).andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(jsonPath("$.size()").value(SIZE_ARRAY + 1));
    }

    @Test
    @SneakyThrows
    void delete() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", USER_ID))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(jsonPath("$.size()").value(SIZE_ARRAY - 1));
    }

    @Test
    @SneakyThrows
    void update(){
        String updateUser = new ObjectMapper()
                .writeValueAsString(new UserCreateUpdateDto("El Homer", null));

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}",USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUser))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", USER_ID))
                .andExpect(jsonPath("$.firstname").value("El Homer"));


    }
}
