package ru.ekudashov.service.impl;

import org.springframework.stereotype.Component;
import ru.ekudashov.service.FrequencyService;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class FrequencyServiceImpl implements FrequencyService {

    @Override
    public Map<String, Long> compute(String text) {

        return Pattern.compile("")
                .splitAsStream(text)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
