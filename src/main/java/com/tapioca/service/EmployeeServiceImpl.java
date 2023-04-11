package com.tapioca.service;

import com.tapioca.entity.Employee;
import com.tapioca.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public void create(Employee employee) {
        repository.save(employee);
    }

    @Override
    public void update(Employee employee) {
    }

    @Override
    public List<Employee> retrieveAll() {
        return null;
    }

}
