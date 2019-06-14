package com.example.demo.controller;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Project;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


/*This simple interface has one method: toResource(). it is based on converting a
 * non-resource object(Employee) into a resource-based object (Resource<Employee>)*/
@Component
public class ProjectResourceAssembler implements ResourceAssembler<Project, Resource<Project>> {

    @Override
    public  Resource<Project> toResource(Project project){
        return new Resource<>(project,
                linkTo(methodOn(ProjectController.class).one(project.getProjectID())).withSelfRel(),
                linkTo(methodOn(ProjectController.class).all()).withRel("project"),
                linkTo(methodOn(ProjectController.class).getEmployee(project.getProjectID())).withRel("employees")


        );
    }

}
