package com.example.demo.Exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long id){
        super("Could not find project "+ id);
    }
}
