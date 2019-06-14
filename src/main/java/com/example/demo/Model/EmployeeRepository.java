package com.example.demo.Model;

import com.example.demo.Model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Resources;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   // List<Resources<Employee>> findByProjects(Long projectID, Pageable pageable);
}
