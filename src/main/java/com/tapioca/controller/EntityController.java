package com.tapioca.controller;

import com.tapioca.entity.Employee;
import com.tapioca.service.EmployeeService;
import com.tapioca.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EntityController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String displayHomepage() {
        return "index";
    }

    @GetMapping("/employees/new")
    public String displayAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/employees/save")
    public String createOrUpdateEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        String[] fieldsToCheck = {"firstName", "middleName", "lastName", "birthDate", "position"};
        for (String field : fieldsToCheck) {
            if (result.hasFieldErrors(field)) {
                model.addAttribute("errorMessage", result.getFieldError(field).getDefaultMessage());
                return employee.getId() == null ? "add-employee" : "view-employee-details";
            }
        }

        if (employeeService.isEmployeeExisting(employee.getFirstName(), employee.getMiddleName(), employee.getLastName())) {
            model.addAttribute("errorMessage", ErrorMessage.EMPLOYEE_EXISTS.getMessage());
            return employee.getId() == null ? "add-employee" : "view-employee-details";
        }

        employeeService.save(employee);
        return "redirect:/employees/list/?success";
    }

    @GetMapping("/employees/list")
    public String displayEmployeeListPage(Model model) {
        model.addAttribute("employees", employeeService.retrieveAll());
        return "view-employees";
    }

    @GetMapping("/employees/{id}")
    public String displayEmployeeDetails(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.retrieveEmployeeById(id);

        if (employee == null)
            return "redirect:/employees/list/?employeeNotFound";

        model.addAttribute("employee", employee);
        return "view-employee-details";
    }

}
