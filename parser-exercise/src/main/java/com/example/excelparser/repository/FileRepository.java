package com.example.excelparser.repository;

import com.example.excelparser.model.Employee;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.List;

public interface FileRepository {
    String save(String fileName, InputStream content);
    String save(String fileName, XSSFWorkbook workbook);
    List<Employee> load(String filePath);
}
