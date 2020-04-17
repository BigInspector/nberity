package com.nberity.application.authentication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {

    private List<Employee> employees = createList();

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        System.out.println("Huh");
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
    public List<Employee> firstPage() {
        System.out.println("Employee: " + employees.get(0).getName());
        return employees;
    }

    @DeleteMapping(path = { "/employees/{id}" })
    public Employee delete(@PathVariable("id") int id) {
        System.out.println("ID: " + id);
        System.out.println("Size: " + employees.size());
        System.out.println("LOL");
        Employee deletedEmp = null;
        for (Employee emp : employees) {
            System.out.println("Jesse");
            if (emp.getEmpId().equals(Integer.toString(id))) {
                System.out.println("LMAO");
                employees.remove(emp);
                deletedEmp = emp;
                break;
            }
        }
        System.out.println("Size 2: " + employees.size());
        return deletedEmp;
    }

    @RequestMapping(value = "/employees/addEntry", method = RequestMethod.POST)
    public Employee create(@RequestBody Employee user) {
        employees.add(user);
        System.out.println(employees);
        return user;
    }

    private List<Employee> createList() {
        List<Employee> tempEmployees = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setName("emp1");
        emp1.setDesignation("manager");
        emp1.setEmpId("1");
        emp1.setSalary(3000);

        Employee emp2 = new Employee();
        emp2.setName("emp2");
        emp2.setDesignation("developer");
        emp2.setEmpId("2");
        emp2.setSalary(3000);
        tempEmployees.add(emp1);
        tempEmployees.add(emp2);
        return tempEmployees;
    }
}
