package vj.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vj.rest.service.EmployeeService;
import vj.rest.entities.Employee;

import java.util.List;

//File created by vijayvishwakarma on 3/22/20

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAll(){
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Employee find(@PathVariable("id") String id){
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee emp){
        return service.create(emp);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Employee update(@PathVariable("id") String id, @RequestBody Employee emp){
        return service.update(id, emp);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }

}

