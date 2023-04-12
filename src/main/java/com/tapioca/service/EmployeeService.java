package com.tapioca.service;

import com.tapioca.entity.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * Method to create a new employee.
     * @param employee - the Employee to be added.
     */
    void create(Employee employee);

    /**
     * Method to update an existing employee.
     * @param employee - the Employee to be updated.
     */
    void update(Employee employee);

    /**
     * Method to view all employees.
     */
    List<Employee> retrieveAll();

    /**
     * Method to validate if the Employee is already existing.
     * @param firstName - first name
     * @param middleName - middle name
     * @param lastName - last name
     * @return - True if already existing
     */
    boolean isEmployeeExisting(String firstName, String middleName, String lastName);
}
