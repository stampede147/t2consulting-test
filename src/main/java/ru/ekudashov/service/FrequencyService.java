package ru.ekudashov.service;

import java.util.Map;

public interface FrequencyService {

    public Map<? extends CharSequence, ? extends Number> compute(String text);
}
