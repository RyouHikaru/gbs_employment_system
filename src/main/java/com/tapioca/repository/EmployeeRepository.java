package com.tapioca.repository;

import com.tapioca.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);
}
