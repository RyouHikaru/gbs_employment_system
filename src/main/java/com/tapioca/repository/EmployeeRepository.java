package com.tapioca.repository;

import com.tapioca.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);

    @Query("SELECT employee FROM Employee employee " +
            "WHERE employee.firstName LIKE %:firstName% " +
            "AND employee.lastName LIKE %:lastName% " +
            "AND employee.position LIKE %:position%")
    List<Employee> findBySearchCriteria(String firstName, String lastName, String position);
}
