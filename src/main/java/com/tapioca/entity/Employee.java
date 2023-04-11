package com.tapioca.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "First name should only contain alphabetical characters")
    private String firstName;

    @Column
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "Middle name should only contain alphabetical characters")
    private String middleName;

    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "Last name should only contain alphabetical characters")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate should not be in the future")
    private LocalDate birthDate;

    @Column(nullable = false)
    @NotBlank(message = "Position is required")
    private String position;

    public Employee() {
    }

    public Employee(Long id, String firstName, String middleName, String lastName, LocalDate birthDate, String position) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", position='" + position + '\'' +
                '}';
    }
}
