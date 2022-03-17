package ru.forsh.sweater.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errorMap = bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors
                        .toMap(FieldError::getField, FieldError::getDefaultMessage));
        return errorMap;
    }
}
