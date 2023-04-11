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
}
