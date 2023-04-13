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
    public void save(Employee employee) {
        repository.save(employee);
    }

    @Override
    public List<Employee> retrieveAll() {
        return repository.findAll();
    }

    @Override
    public Employee retrieveEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean isEmployeeExisting(String firstName, String middleName, String lastName) {
        return repository.existsByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName);
    }

}
