package ru.ekudashov.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class FrequencyServiceImplTest {

    public FrequencyServiceImpl sut = new FrequencyServiceImpl();

    @Test
    public void parse_returns_empty_list_when_empty_input() {

        String text = "";

        Map<String, Long> result = sut.compute(text);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void parse_returns_values_in_descending_order() {

        String text = "aaabbc";

        Map<String, Long> result = sut.compute(text);

        Assertions.assertTrue(result.values().stream().allMatch(Objects::nonNull));
        Assertions.assertIterableEquals(sortByDescending(result.values()), result.values());
    }

    private static List<Long> sortByDescending(Collection<Long> values) {
        return values
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

}