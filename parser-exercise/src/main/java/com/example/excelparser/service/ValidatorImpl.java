package com.example.excelparser.service;

import com.example.excelparser.model.EmployeeDescriptor;
import com.example.excelparser.model.ParserResult;
import com.example.excelparser.model.Status;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ValidatorImpl implements Validator {
    @Override
    @SneakyThrows
    public ParserResult validate(List<EmployeeDescriptor> employees, String fileName) {
        Map<Boolean, Long> collect = employees.stream()
                .collect(Collectors.partitioningBy(this::isValidEmployee, Collectors.counting()));
        Long validRows = collect.get(true);
        Long invalidRows = collect.get(false);
        Status status = validRows - invalidRows > 0 ? Status.PASS : Status.FAIL;
        return new ParserResult(fileName, validRows, invalidRows, status);
    }

    private boolean isValidEmployee(EmployeeDescriptor employee) {
        return isValidName(employee.getName()) && isValidID(employee.getId());
    }

    private boolean isValidName(String name) {
        return name != null && name.length() > 2;
    }

    private boolean isValidID(int employeeId) {
        return employeeId > 0;
    }
}
