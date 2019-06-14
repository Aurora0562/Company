package com.example.demo.controller;

import com.example.demo.Exception.ProjectNotFoundException;
import com.example.demo.Model.Employee;
import com.example.demo.Exception.EmployeeNotFoundException;
import com.example.demo.Model.EmployeeRepository;

import com.example.demo.Model.Project;
import com.example.demo.Model.ProjectRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class ProjectController {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ProjectResourceAssembler assembler;

    public ProjectController(EmployeeRepository repository, ProjectResourceAssembler assembler,
                              ProjectRepository projectRepository){
        this.employeeRepository = repository;
        this.projectRepository = projectRepository;
        this.assembler = assembler;
    }

    @GetMapping("/projects")
    Resources<Resource<Project>> all(){
        List<Resource<Project>> project =projectRepository.findAll().stream()
                .map(assembler::toResource).collect(Collectors.toList());


        return new Resources<>(project,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @GetMapping("/projects/{id}")
    Resource<Project> one(@PathVariable Long id)  {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new ProjectNotFoundException(id));
        return assembler.toResource(project);
        //return new Resource<>(employee);

    }
    ///？？？？？ need to be improved!!!!
    @GetMapping("/projects/{id}/employees")
    public Set<Employee> getEmployee(@PathVariable(value = "id") Long projectID){

        return this.projectRepository.findById(projectID).map(project -> {
            return project.getEmployees();
        }).orElseThrow(()->new ProjectNotFoundException(projectID));
    }
//
//    @PostMapping("/employees")
//    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException{
//        Resource<Employee> resource = assembler.toResource(employeeRepository.save(newEmployee));
//
//        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
//    }
//
//
//
//
//    @PutMapping("/employees/{id}")
//    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) throws URISyntaxException {
//        Employee updatedEmployee = employeeRepository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
//                    return employeeRepository.save(newEmployee);
//                }).orElseGet(()-> {
//                    newEmployee.setEmployeeid(id);
//                    return employeeRepository.save(newEmployee);
//                });
//        Resource<Employee> resource = assembler.toResource(updatedEmployee);
//
//        return ResponseEntity
//                .created(new URI(resource.getId().expand().getHref()))
//                .body(resource);
//    }
//
//    @DeleteMapping("/employees/{id}")
//    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
//
//        employeeRepository.deleteById(id);
//
//        return ResponseEntity.noContent().build();
//    }

}


