package com.example.excelparser.service;

import com.example.excelparser.model.EmployeeDescriptor;
import com.example.excelparser.model.ParserResult;

import java.io.InputStream;
import java.util.List;

public interface ParserService {
    String uploadFile(String fileName, InputStream content);

    String uploadFile(String fileName, List<EmployeeDescriptor> employees);

    ParserResult validate(String filePath);
}
