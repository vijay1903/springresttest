package vj.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vj.rest.entities.Employee;
import vj.rest.exceptions.BadRequestException;
import vj.rest.exceptions.EmployeeNotFoundException;
import vj.rest.exceptions.ResourceNotFoundException;
import vj.rest.repository.EmployeeRepository;


import java.util.List;
import java.util.Optional;

//File created by vijayvishwakarma on 3/25/20
@Service
public class EmployeeServiceImplementation implements EmployeeService{

    @Autowired
    EmployeeRepository repository;

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return (List<Employee>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findOne(String id){
            return repository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("Employee not found for id "+id)
            );
    }

    @Transactional
    public Employee create(Employee emp) {
        Optional<Employee> existing = repository.findByEmail(emp.getEmail());
        if(existing.isPresent()){
            throw new BadRequestException("Employee with email "+ emp.getEmail() +" already exists.");
        }
        return repository.save(emp);
    }

    @Transactional
    public Employee update(String id, Employee emp) {
        Optional<Employee> existing = repository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Employee with id "+id+" not found.");
        }
        existing = repository.findByEmail(emp.getEmail());
        if(existing.isPresent()){
            throw new BadRequestException("Employee with email "+ emp.getEmail() +" already exists.");
        }
        return repository.save(emp);
    }

    @Transactional
    public void delete(String id) {
        Optional<Employee> existing = repository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Employee with id "+id+" not found.");
        }
        repository.delete(existing.get());
    }
}
