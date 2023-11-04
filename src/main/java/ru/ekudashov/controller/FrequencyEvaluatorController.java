package ru.ekudashov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ekudashov.service.FrequencyService;

import java.util.Map;

@RestController
@RequestMapping("/evaluators/frequency")
public class FrequencyEvaluatorController {

    private final FrequencyService frequencyService;

    @Autowired
    public FrequencyEvaluatorController(FrequencyService textFormatService) {
        this.frequencyService = textFormatService;
    }

    @GetMapping(params = "text",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<? extends CharSequence, ? extends Number> format(@RequestParam String text) {

        return frequencyService.compute(text);
    }

}
