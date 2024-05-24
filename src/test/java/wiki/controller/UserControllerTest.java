package wiki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import wiki.dto.user.AuthDto;
import wiki.dto.user.NewUserDto;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void createUser() throws Exception {
        NewUserDto requestBody = new NewUserDto();
        requestBody.setLogin("login");
        requestBody.setPassword("pass");
        MockHttpServletRequestBuilder request = post("/user/create")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(requestBody));
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void login() throws Exception {
        AuthDto requestBody = new AuthDto();
        requestBody.setLogin("login");
        requestBody.setPassword("pass");
        MockHttpServletRequestBuilder request = post("/user/login")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(requestBody));
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}