package com.tapioca.repository;

import com.tapioca.entity.Compensation;
import com.tapioca.entity.Employee;
import com.tapioca.utils.CompensationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long> {

    boolean existsByTypeAndDateAndEmployee(CompensationType type, LocalDate date, Employee employee);
}
