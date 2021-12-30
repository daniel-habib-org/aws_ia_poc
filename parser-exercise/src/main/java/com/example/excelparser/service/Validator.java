package com.example.excelparser.service;

import com.example.excelparser.model.EmployeeDescriptor;
import com.example.excelparser.model.ParserResult;

import java.util.List;

public interface Validator {
    ParserResult validate(List<EmployeeDescriptor> employees, String fileName);
}
