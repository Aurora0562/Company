package com.example.demo.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "projectEmployee")
public class Project {
    private @Getter @Setter @Id @GeneratedValue Long ProjectID;
    private @Getter @Setter String ProjectName;
    private @Getter @Setter Status status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Company", joinColumns = @JoinColumn(name = "EmployeeID"),
            inverseJoinColumns = @JoinColumn(name = "ProjectID"))
    private Set<Employee> employees;

    public Project(){}

    public Project(String ProjectName, Status status){
        this.ProjectName = ProjectName;
        this.status = status;
    }
    public Project(String projectName, Status status, Set<Employee> employees){
        this(projectName, status);
        this.employees = employees;
    }
}
