package com.example.excelparser.repository;

import com.example.excelparser.model.Employee;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class LocalFileRepository implements FileRepository {



    @Value("${base.dir}")
    private String dir;


    @Override
    @SneakyThrows
    public String save(String fileName, InputStream content) {
        String filePath = dir + fileName;
        FileOutputStream file = new FileOutputStream(filePath);
        file.write(content.readAllBytes());
        file.flush();
        file.close();
        return filePath;
    }

    @Override
    @SneakyThrows
    public String save(String fileName,XSSFWorkbook workbook) {
        String filePath = dir + fileName;

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);
        out.close();
        return filePath;
    }

    @Override
    @SneakyThrows
    public  List<Employee> load(String filePath){
            File file = new File(filePath);
            if(!file.exists()) throw new FileNotFoundException("File: "+filePath+" Doesn't exist");
            FileInputStream excelFile = new FileInputStream(file);

            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Employee> employees = new ArrayList<>();

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Employee employee = new Employee();
                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if(cellIndex==0) {
                        employee.setName(currentCell.getStringCellValue());
                    } else if(cellIndex==1) {
                        employee.setId((int) currentCell.getNumericCellValue());
                    }

                    cellIndex++;
                }

                employees.add(employee);
            }
            workbook.close();
            return employees;

    }
}
