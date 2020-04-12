package vj.rest.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import vj.rest.entities.Employee;
import vj.rest.exceptions.BadRequestException;
import vj.rest.exceptions.EmployeeNotFoundException;
import vj.rest.exceptions.ResourceNotFoundException;
import vj.rest.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplementationTest {

    @TestConfiguration
    static class EmployeeServiceImplementationTestConfig {

        @Bean
        public EmployeeService getService(){
            return new EmployeeServiceImplementation();
        }
    }

    @Autowired
    private EmployeeService service;

    @MockBean
    private EmployeeRepository repository;

    private List<Employee> testEmployees;

    @Before
    public void setup(){
        Employee emp = new Employee();
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setEmail("jdoe@mail.com");
        testEmployees = Collections.singletonList(emp);
        //when
        Mockito.when(repository.findAll()).thenReturn(testEmployees);

        Mockito.when(repository.findById("")).thenReturn(Optional.of(emp));
        //return
    }

    @After
    public void cleanUp(){
    }

    @Test
    public void findAll() {
        List<Employee> result = service.findAll();
        Assert.assertEquals("Cannot get all the employees", Collections.emptyList(), result);
    }

    @Test
    public void findOne() {
        Employee result = service.findOne(testEmployees.get(0).getId());
        Assert.assertEquals("employee should match", testEmployees.get(0), result);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void findOneNotFound() {
        Employee result = service.findOne("invalid id");
    }

    @Test
    public void create() {
//        Employee emp = new Employee();
//        emp.setFirstName("John");
//        emp.setLastName("Doe");
//        emp.setIncome(10000);
//        emp.setEmail("jdoe@mail.com");
//        Employee result = service.create(emp);
//        Assert.assertEquals("employee should match", emp, result);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}