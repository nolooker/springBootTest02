package com.computer.controller;

import com.computer.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployTestController {
    @GetMapping("/employee")
    public Employee test(){
        Employee bean = new Employee();
        bean.setAge(30);
        bean.setId("hohoho");
        bean.setName("HHa");
        return bean;
    }
}
