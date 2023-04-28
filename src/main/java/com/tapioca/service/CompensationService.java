package com.tapioca.service;

import com.tapioca.entity.Compensation;
import com.tapioca.entity.MonthlyCompensation;
import com.tapioca.utils.CompensationSearchCriteria;

import java.time.LocalDate;
import java.util.List;

public interface CompensationService {

    /**
     * Method to save a new or existing Compensation.
     *
     * @param compensation - the Compensation to be saved.
     */
    void save(Compensation compensation);

    /**
     * Method to validate if the Employee already has a Salary for the given month.
     *
     * @param compensation - the Compensation to be checked
     * @return - True if a duplicate entry is found
     */
    boolean hasDuplicateSalaryEntry(Compensation compensation);

    /**
     * Method to calculate total amount of Compensation per month of Employee.
     *
     * @param criteria - the Compensation Search criteria.
     * @return - the list of MonthlyCompensation matching the criteria
     */
    List<MonthlyCompensation> getTotalAmountPerMonthByCriteria(CompensationSearchCriteria criteria);

    /**
     * Method to retrieve Compensations by employee ID and date.
     *
     * @param employeeId - the employee ID.
     * @param date - the date specified.
     * @return - the list of Compensation by date.
     */
    List<Compensation> getCompensationsByDate(Long employeeId, LocalDate date);
}
