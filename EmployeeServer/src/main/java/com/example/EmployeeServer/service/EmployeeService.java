package com.example.EmployeeServer.service;

import com.example.EmployeeServer.dto.EmployeeDto;
import com.example.EmployeeServer.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployees();

    Employee updateEmployee(EmployeeDto employeeDto, Integer id);

    Employee deleteEmployee(Integer id);

    Employee getEmployeeById(Integer id);

    boolean getEmployeeByEmail(String email);

    boolean getEmployeeByMobile(String mobile);

}
