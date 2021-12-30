package com.example.excelparser.mapper;

import com.example.excelparser.model.EmployeeDescriptor;
import com.example.excelparser.model.EmployeeDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EmployeeDtoToEmployeeDescriptorMapper {
    EmployeeDescriptor employeeDtoToEmployeeDescriptor(EmployeeDto source);
    EmployeeDto employeeDescriptorToEmployeeDto(EmployeeDescriptor destination);
}
