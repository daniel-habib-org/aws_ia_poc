package com.example.excelparser.service;

import com.example.excelparser.mapper.EmployeeDescriptorToEmployeeMapper;
import com.example.excelparser.model.EmployeeDescriptor;
import com.example.excelparser.model.ParserResult;
import com.example.excelparser.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelParserService implements ParserService {

    private final FileRepository repository;
    private final Validator validator;
    private final EmployeeDescriptorToEmployeeMapper mapper;
    public static final String DEFAULT_SHEET_NAME = "sheet1";

    @Override
    public String uploadFile(String fileName, InputStream content) {
        return repository.save(fileName, content);
    }

    @Override
    public String uploadFile(String fileName, List<EmployeeDescriptor> employees) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet(DEFAULT_SHEET_NAME);
        int rownum = 0;
        for (EmployeeDescriptor employee : employees) {
            Row row = sheet.createRow(rownum++);
            createRow(employee, row);

        }
        return repository.save(fileName, workbook);
    }

    private  void createRow(EmployeeDescriptor employee, Row row)
    {
        Cell cell = row.createCell(0);
        cell.setCellValue(employee.getName());
        cell = row.createCell(1);
        cell.setCellValue(employee.getId());
    }

    @Override
    @SneakyThrows
    public ParserResult validate(String filePath) {
        List<EmployeeDescriptor> employees = repository.load(filePath).stream().map(mapper::employeeDescriptorToEmployee).collect(Collectors.toList());
        return validator.validate(employees, Paths.get(filePath).getFileName().toString());
    }


}
