package com.example.demo.Model;

import com.example.demo.Model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
   // Page<Project> findByEmployees(Long EmployeeID, Pageable pageable);
}
