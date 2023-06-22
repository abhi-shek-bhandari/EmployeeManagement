package com.example.EmployeeServer.repo;

import com.example.EmployeeServer.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByMobile(String email);

}
