package com.tapioca.service;

import com.tapioca.entity.Compensation;
import com.tapioca.repository.CompensationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
