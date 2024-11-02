package com.emezon.stock.infra.input.rest;

import com.emezon.stock.infra.inbound.rest.constants.RestApiConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PingControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simulates HTTP requests

    @Test
    void testPing() throws Exception {
        // Act & Assert
        mockMvc.perform(get(RestApiConstants.API_PING))
                .andExpect(status().isOk())
                .andExpect(content().string("pong"));
    }

}
