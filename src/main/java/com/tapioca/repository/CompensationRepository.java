package com.tapioca.repository;

import com.tapioca.entity.Compensation;
import com.tapioca.entity.Employee;
import com.tapioca.entity.MonthlyCompensation;
import com.tapioca.utils.CompensationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long> {

    boolean existsByTypeAndDateAndEmployee(CompensationType type, LocalDate date, Employee employee);

    @Query("SELECT new com.tapioca.entity.MonthlyCompensation(YEAR(compensation.date), MONTH(compensation.date), SUM(compensation.amount)) " +
            "FROM Compensation compensation " +
            "WHERE compensation.date BETWEEN :startDate AND :endDate " +
            "AND compensation.employee.id = :employeeId " +
            "GROUP BY YEAR(compensation.date), MONTH(compensation.date)")
    List<MonthlyCompensation> findBySearchCriteria(LocalDate startDate, LocalDate endDate, Long employeeId);

    @Query("SELECT compensation FROM Compensation compensation " +
            "WHERE compensation.employee.id = :employeeId " +
            "AND MONTH(compensation.date) = MONTH(:date)")
    List<Compensation> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}
