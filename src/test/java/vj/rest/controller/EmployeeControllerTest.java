package vj.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vj.rest.entities.Employee;
import vj.rest.repository.EmployeeRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private ObjectMapper om;

    @Before
    public void setup(){
        Employee emp = new Employee();
        emp.setId("1");
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setEmail("jdoe@mail.com");
        repo.save(emp);
        emp = new Employee();
        emp.setId("2");
        emp.setFirstName("John");
        emp.setLastName("Doe 2");
        emp.setEmail("jdoe2@mail.com");
        repo.save(emp);
    }

    @After
    public void clean(){
        repo.deleteAll();
    }

    @Test
    public void findAll() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void find() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("jdoe@mail.com")));
    }

    @Test
    public void find404() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/employees/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void create() throws Exception {
        Employee emp = new Employee();
        emp.setId("3");
        emp.setFirstName("John");
        emp.setLastName("Doe 3");
        emp.setEmail("jdoe3@mail.com");
        mvc.perform(MockMvcRequestBuilders
        .post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsBytes(emp)))
        .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("jdoe3@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()));
        mvc.perform(MockMvcRequestBuilders
                .get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void update() throws Exception {
        Employee emp = new Employee();
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setEmail("jdoe_new@mail.com");
        mvc.perform(MockMvcRequestBuilders
                .put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsBytes(emp)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("jdoe_new@mail.com")));
    }

    @Test
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}