package com.example.excelparser.mapper;

import com.example.excelparser.model.Employee;
import com.example.excelparser.model.EmployeeDescriptor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeDescriptorToEmployeeMapper {
    Employee employeeDescriptorToEmployee(EmployeeDescriptor source);
    EmployeeDescriptor employeeDescriptorToEmployee(Employee destination);
}
