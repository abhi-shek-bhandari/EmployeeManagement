package com.example.EmployeeServer.service;

import com.example.EmployeeServer.Exception.EmployeeException;
import com.example.EmployeeServer.dto.EmployeeDto;
import com.example.EmployeeServer.model.Employee;
import com.example.EmployeeServer.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = this.dtoToEmployee(employeeDto);

        if (this.getEmployeeByEmail(employee.getEmail()))
            throw new EmployeeException("Employee Email Already Exists " + employee.getEmail());
        if (this.getEmployeeByMobile(employee.getMobile()))
            throw new EmployeeException("Employee Mobile Already Exists " + employee.getMobile());

        return this.employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepo.findAll();
    }

    @Override
    public Employee updateEmployee(EmployeeDto employeeDto, Integer id) {
        Employee employee = this.employeeRepo.findById(id)
                .orElseThrow(()-> new EmployeeException("No Employee with Id: " + id));

        if (employeeDto.getFirstName().length() != 0){
            employee.setFirstName(employeeDto.getFirstName());
        }
        if (employeeDto.getLastName().length() != 0){
            employee.setLastName(employeeDto.getLastName());
        }
        if (employeeDto.getEmail().length() != 0){
            employee.setEmail(employeeDto.getEmail());
        }
        if (employeeDto.getMobile().length() != 0){
            employee.setMobile(employeeDto.getMobile());
        }
        this.employeeRepo.save(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(Integer id) {
        Employee employee = this.employeeRepo.findById(id)
                .orElseThrow(()-> new EmployeeException("No Employee with Id: " + id));

        this.employeeRepo.delete(employee);

        return employee;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return this.employeeRepo.findById(id)
                .orElseThrow(()-> new EmployeeException("No Employee with Id: " + id));
    }

    @Override
    public boolean getEmployeeByEmail(String email) {
        Optional<Employee> employee = this.employeeRepo.findByEmail(email);
        return employee.isPresent();
    }

    @Override
    public boolean getEmployeeByMobile(String mobile) {
        Optional<Employee> employee = this.employeeRepo.findByMobile(mobile);
        return employee.isPresent();
    }

    Employee dtoToEmployee(EmployeeDto employeeDto){

        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobile(employeeDto.getMobile());

        return employee;
    }
}
