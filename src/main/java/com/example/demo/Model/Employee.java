package com.example.demo.Model;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
//@Table(name="EmployeeProject")
public class Employee {

    private @Getter @Setter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long Employeeid;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String role;

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects;

    public Employee() {}

    public Employee(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Employee(String firstName, String lastName, String role, Set<Project>projects){
        this(firstName, lastName, role);
        this.projects = projects;

    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts =name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    @Override
    public String toString(){
        return String.format("[EmployeeID = %d," +
                "EmployeeName = %s," +
                "EmployeeRole = %s", getEmployeeid(), getName(),getRole());
    }
}
