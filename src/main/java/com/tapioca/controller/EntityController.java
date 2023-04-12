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

    @GetMapping("/employee/new")
    public String displayAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/employee/new")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        String[] fieldsToCheck = {"firstName", "middleName", "lastName", "birthDate", "position"};
        for (String field : fieldsToCheck) {
            if (result.hasFieldErrors(field)) {
                model.addAttribute("errorMessage", result.getFieldError(field).getDefaultMessage());
                return "add-employee";
            }
        }

        if (employeeService.isEmployeeExisting(employee.getFirstName(), employee.getMiddleName(), employee.getLastName())) {
            model.addAttribute("errorMessage", ErrorMessage.EMPLOYEE_EXISTS.getMessage());
            return "add-employee";
        }

        employeeService.create(employee);
        return "redirect:/employee/list/?success";
    }

    @GetMapping("/employee/list")
    public String displayEmployeeListPage(Model model) {
        model.addAttribute("employees", employeeService.retrieveAll());
        return "view-employees";
    }

}
