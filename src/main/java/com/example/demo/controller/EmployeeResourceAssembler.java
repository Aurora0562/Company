package com.example.demo.controller;

import com.example.demo.Model.Employee;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


/*This simple interface has one method: toResource(). it is based on converting a
* non-resource object(Employee) into a resource-based object (Resource<Employee>)*/
@Component
public class EmployeeResourceAssembler implements ResourceAssembler<Employee, Resource<Employee>> {

    @Override
    public  Resource<Employee> toResource(Employee employee){
        return new Resource<>(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getEmployeeid())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"),
                linkTo(methodOn(EmployeeController.class).getProject(employee.getEmployeeid())).withRel("projects")
        );
    }

}
