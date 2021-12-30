package com.example.excelparser.controller;

import com.example.excelparser.mapper.EmployeeDtoToEmployeeDescriptorMapper;
import com.example.excelparser.model.EmployeeDto;
import com.example.excelparser.model.ParserResult;
import com.example.excelparser.service.ParserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ParserController {

    private final ParserService parser;
    private final EmployeeDtoToEmployeeDescriptorMapper mapper;

    @GetMapping("/parser/{filePath}")
    ParserResult validateFile(@PathVariable String filePath) {
        filePath = UriUtils.decode(filePath, "UTF-8");
        return parser.validate(filePath);
    }

    @SneakyThrows
    @PostMapping(value = "/parser")
    String uploadFile(@RequestParam String file, InputStream content) {
        return parser.uploadFile(file, content);
    }

    @SneakyThrows
    @PostMapping(value = "/parser/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    String uploadFile(@RequestParam String file, @RequestBody List<EmployeeDto> employees) {
        return parser.uploadFile(file, employees.stream().map(mapper::employeeDtoToEmployeeDescriptor).collect(Collectors.toList()));
    }
}