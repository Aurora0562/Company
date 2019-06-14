package com.example.demo;

import com.example.demo.Model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Slf4j
@Transactional
public class EmployeeApplication implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        Employee e1 = new Employee("Bilbo", "Baggins", "analyst");
        Employee e2 = new Employee("Frodo", "Baggins", "security");
        Employee e3 = new Employee("Aurora", "Yang", "programmer");
        Project p1 = new Project("Java Development", Status.COMPLETED, new HashSet<>(Arrays.asList(e1, e2)));
        Project p2 = new Project("Web Developemnt", Status.AVAILABLE, new HashSet<>(Arrays.asList(e1, e3)));


        Project p3 = new Project("Game Development", Status.IN_PROGRESS);
        Project p4 = new Project("Data Analysis", Status.IN_PROGRESS);
        Project p5 = new Project("Network Security", Status.AVAILABLE);
        Employee e4 = new Employee("Jin", "Chen", "Security", new HashSet<>(Arrays.asList(p3, p4)));
        Employee e5 = new Employee("Garrin", "Wang"," programmer", new HashSet<>(Arrays.asList(p3, p4, p5)));


        projectRepository.saveAll(Arrays.asList(p1,p2, p3, p4, p5));
        employeeRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));

        projectRepository.findAll().forEach(project ->{
            log.info("Preloaded: "+project);
        });

        employeeRepository.findAll().forEach(employee -> {
            log.info("Preloaded " + employee.toString());
        });

    }
}
