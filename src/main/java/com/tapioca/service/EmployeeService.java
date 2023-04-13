package com.tapioca.service;

import com.tapioca.entity.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * Method to save a new or existing Employee.
     * @param employee - the Employee to be saved.
     */
    void save(Employee employee);

    /**
     * Method to view all employees.
     */
    List<Employee> retrieveAll();

    /**
     * Method to view one employee by ID.
     * @param id - id of the Employee.
     * @return - The retrieved Employee.
     */
    Employee retrieveEmployeeById(Long id);

    /**
     * Method to validate if the Employee is already existing.
     * @param firstName - first name
     * @param middleName - middle name
     * @param lastName - last name
     * @return - True if already existing
     */
    boolean isEmployeeExisting(String firstName, String middleName, String lastName);
}
