package ru.ekudashov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ekudashov.service.FrequencyService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FrequencyEvaluatorControllerTest {

    @MockBean
    FrequencyService frequencyService;

    @Autowired
    MockMvc mvc;

    @Test
    void format_returns_OK() throws Exception {

        final String text = "aaabbc";

        Mockito.when(this.frequencyService.compute(text))
                .thenReturn((Map) getExpectedMap());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/evaluators/frequency?text={text}", text)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        Assertions.assertEquals("{\"a\":3,\"b\":2,\"c\":1}", result.getResponse().getContentAsString());
    }

    private static HashMap<String, Long> getExpectedMap() {
        final HashMap<String, Long> expected = new HashMap<>();
        expected.put("a", 3L);
        expected.put("b", 2L);
        expected.put("c", 1L);
        return expected;
    }
}