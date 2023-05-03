package com.tapioca.service;

import com.tapioca.entity.Employee;
import com.tapioca.utils.EmployeeSearchCriteria;

import java.util.List;

public interface EmployeeService {

    /**
     * Method to save a new or existing Employee.
     * @param employee - the Employee to be saved.
     */
    void save(Employee employee);

    /**
     * Method to view all employees by search criteria
     * @param criteria - search criteria of Employee
     * @return - the list of Employees matching the criteria
     */
    List<Employee> retrieveEmployeeBySearchCriteria(EmployeeSearchCriteria criteria);

    /**
     * Method to view one employee by ID.
     * @param id - id of the Employee.
     * @return - The retrieved Employee.
     */
    Employee retrieveEmployeeById(Long id);


    /**
     * Method to retrieve an Employee ID by name.
     * @param firstName - first name.
     * @param middleName - middle name.
     * @param lastName - last name.
     * @return - The retrieved ID of Employee.
     */
    Long retrieveIdByName(String firstName, String middleName, String lastName);

    /**
     * Method to validate if the Employee is already existing.
     * @param firstName - first name
     * @param middleName - middle name
     * @param lastName - last name
     * @return - True if already existing
     */
    boolean isEmployeeExisting(String firstName, String middleName, String lastName);
}
