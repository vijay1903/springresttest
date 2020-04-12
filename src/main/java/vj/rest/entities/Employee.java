package vj.rest.entities;

import javax.persistence.*;
import java.util.UUID;

//File created by vijayvishwakarma on 3/30/20

@Entity
public class Employee {


    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private String firstName;
    private String lastName;
    private String email;

    public void setIncome(float income) {
        this.income = income;
    }

    private float income;

    public Employee() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getIncome() {
        return income;
    }
}
