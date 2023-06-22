package com.example.EmployeeServer.Controller;

import com.example.EmployeeServer.dto.EmployeeDto;
import com.example.EmployeeServer.model.Employee;
import com.example.EmployeeServer.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("<h1>Hello, Welcome to My Employee app</h1>", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployeeHandler(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(this.employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployeesHandler(){
        return new ResponseEntity<>(this.employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployeeHandler(@RequestBody EmployeeDto employeeDto,@PathVariable Integer id){
        return new ResponseEntity<>(this.employeeService.updateEmployee(employeeDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Employee> deleteEmployeeHandler(@PathVariable Integer id){
        return new ResponseEntity<>(this.employeeService.deleteEmployee(id), HttpStatus.OK);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeByIdHandler(@PathVariable Integer id){
        return new ResponseEntity<>(this.employeeService.getEmployeeById(id), HttpStatus.OK);
    }
}
