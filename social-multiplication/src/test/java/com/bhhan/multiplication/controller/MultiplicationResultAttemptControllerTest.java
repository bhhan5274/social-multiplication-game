package com.bhhan.multiplication.controller;

import com.bhhan.multiplication.domain.Multiplication;
import com.bhhan.multiplication.domain.MultiplicationResultAttempt;
import com.bhhan.multiplication.domain.User;
import com.bhhan.multiplication.event.EventDispatcher;
import com.bhhan.multiplication.repository.MultiplicationResultAttemptRepository;
import com.bhhan.multiplication.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hbh5274@gmail.com on 2020-07-09
 * Github : http://github.com/bhhan5274
 */

@SpringBootTest
@AutoConfigureMockMvc
class MultiplicationResultAttemptControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventDispatcher eventDispatcher;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MultiplicationResultAttemptRepository attemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void postResultReturnCorrect() throws Exception {
        postResult(50, 70, 3500, true);
    }

    @Test
    void postResultReturnNotCorrect() throws Exception {
        postResult(60, 70, 3500, false);
    }

    @Test
    void postResultReturnThrow() throws Exception {
        postResultError(50, 70, 3500);
    }

    @Test
    @Transactional
    void getUserStats() throws Exception {
        saveResultAttempt(50, 70, 3500, true);
        saveResultAttempt(50, 50, 2500, true);
        saveResultAttempt(50, 80, 4000, true);
        saveResultAttempt(50, 30, 1200, false);
        saveResultAttempt(50, 40, 1200, false);
        saveResultAttempt(50, 60, 1200, false);
        saveResultAttempt(50, 20, 1200, false);

        mockMvc.perform(get("/results").param("alias", "john"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void getResultById() throws Exception {
        final int factorA = 50;
        final int factorB = 50;
        final int result = 1234;
        final boolean correct = false;

        final MultiplicationResultAttempt resultAttempt = saveResultAttempt(factorA, factorB, result, correct);

        mockMvc.perform(get("/results/" + resultAttempt.getId().toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.multiplication.factorA").value(factorA))
                .andExpect(jsonPath("$.multiplication.factorB").value(factorB))
                .andExpect(jsonPath("$.resultAttempt").value(result))
                .andExpect(jsonPath("$.correct").value(correct));
    }

    private void postResult(int factorA, int factorB, int result, boolean correct) throws Exception {
        final MultiplicationResultAttempt resultAttempt = makeResultAttempt(factorA, factorB, result, false);

        mockMvc.perform(post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resultAttempt)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct").value(correct));
    }

    private void postResultError(int factorA, int factorB, int result) throws Exception {
        final MultiplicationResultAttempt resultAttempt = makeResultAttempt(factorA, factorB, result, true);

        mockMvc.perform(post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resultAttempt)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private MultiplicationResultAttempt makeResultAttempt(int factorA, int factorB, int result, boolean correct) {
        final User user = makeUser();

        final Multiplication multiplication = Multiplication.builder()
                .factorA(factorA)
                .factorB(factorB)
                .build();

        return MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .correct(correct)
                .resultAttempt(result)
                .build();
    }

    MultiplicationResultAttempt saveResultAttempt(int factorA, int factorB, int result, boolean correct) {
        final User user = makeUser();

        final Multiplication multiplication = Multiplication.builder()
                .factorA(factorA)
                .factorB(factorB)
                .build();

        return attemptRepository.save(MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .correct(correct)
                .resultAttempt(result)
                .build());
    }

    private User makeUser() {
        final Optional<User> user = userRepository.findByAlias("john");
        return user.orElseGet(() -> userRepository.save(User.builder()
                .alias("john")
                .build()));
    }
}