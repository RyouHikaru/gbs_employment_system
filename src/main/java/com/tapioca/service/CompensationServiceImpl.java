package com.tapioca.service;

import com.tapioca.entity.Compensation;
import com.tapioca.entity.MonthlyCompensation;
import com.tapioca.repository.CompensationRepository;
import com.tapioca.utils.CompensationSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompensationServiceImpl implements CompensationService {

    @Autowired
    private CompensationRepository repository;

    @Override
    public void save(Compensation compensation) {
        repository.save(compensation);
    }

    @Override
    public boolean hasDuplicateSalaryEntry(Compensation compensation) {
        return repository.existsByTypeAndDateAndEmployee(compensation.getType(), compensation.getDate(), compensation.getEmployee());
    }

    @Override
    public List<MonthlyCompensation> getTotalAmountPerMonthByCriteria(CompensationSearchCriteria criteria) {
        // Adjust endDate so that it will include the last day of the month
        LocalDate endDate = criteria.getEndDate();
        criteria.setEndDate(endDate.withDayOfMonth(endDate.lengthOfMonth()));
        return repository.findBySearchCriteria(criteria.getStartDate(), criteria.getEndDate(), criteria.getEmployeeId());
    }

    @Override
    public List<Compensation> getCompensationsByDate(Long employeeId, LocalDate date) {
        return repository.findByEmployeeIdAndDate(employeeId, date);
    }

    @Override
    public Compensation getCompensationById(Long id) {
        Optional<Compensation> compensationOptional = repository.findById(id);
        return compensationOptional.orElse(null);
    }
}
