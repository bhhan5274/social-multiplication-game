package com.bhhan.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hbh5274@gmail.com on 2020-07-09
 * Github : http://github.com/bhhan5274
 */

@SpringBootTest
@AutoConfigureMockMvc
class MultiplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRandomMultiplicationTest() throws Exception {
        mockMvc.perform(get("/multiplications/random")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.factorA").exists())
                .andExpect(jsonPath("$.factorB").exists());
    }
}