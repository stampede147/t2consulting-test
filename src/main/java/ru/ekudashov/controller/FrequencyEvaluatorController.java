package ru.ekudashov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ekudashov.service.FrequencyService;

import java.util.Map;


@Tags(value = {
        @Tag(name = "Frequency Evaluator", description = "provides api about evaluating frequency")
})
@RestController
@RequestMapping("/evaluators/frequency")
public class FrequencyEvaluatorController {

    private final FrequencyService frequencyService;

    @Autowired
    public FrequencyEvaluatorController(FrequencyService textFormatService) {
        this.frequencyService = textFormatService;
    }

    @Operation(description = "returns an array of symbols in descending frequency order",
            parameters = @Parameter(name = "text",
                    description = "text to be evaluated",
                    example = "aaabbc"),
            responses = @ApiResponse(responseCode = "200",
                    description = "(OK) returns an array of symbols in descending frequency order",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"a\": 3,\n" +
                                    "    \"b\": 2,\n" +
                                    "    \"c\": 1\n" +
                                    "}"))))
    @GetMapping(params = "text",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<? extends CharSequence, ? extends Number> format(@RequestParam String text) {

        return frequencyService.compute(text);
    }

}
