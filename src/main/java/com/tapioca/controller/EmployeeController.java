package com.tapioca.controller;

import com.tapioca.entity.Employee;
import com.tapioca.service.EmployeeService;
import com.tapioca.utils.EmployeeSearchCriteria;
import com.tapioca.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/**")
    public String redirectToHome() {
        return "redirect:/";
    }

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

        String firstName = employee.getFirstName();
        String middleName = employee.getMiddleName();
        String lastName = employee.getLastName();

        if (employeeService.isEmployeeExisting(firstName, middleName, lastName)) {
            Long retrievedId = employeeService.retrieveIdByName(firstName, middleName, lastName);

            if (!retrievedId.equals(employee.getId())) {
                model.addAttribute("errorMessage", ErrorMessage.EMPLOYEE_EXISTS.getMessage());
                return employee.getId() == null ? "add-employee" : "view-employee-details";
            }

        }

        employeeService.save(employee);
        return "redirect:/employees/list/?success";
    }

    @GetMapping("/employees/list")
    public String displayEmployeeListSearchResults(@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
                                                   @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
                                                   @RequestParam(value = "position", required = false, defaultValue = "") String position,
                                                   Model model) {
        EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(firstName, lastName, position);
        model.addAttribute("search", criteria);
        model.addAttribute("employees", employeeService.retrieveEmployeeBySearchCriteria(criteria));
        return "view-employees";
    }

    @GetMapping("/employees/employee/{id}")
    public String displayEmployeeDetails(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.retrieveEmployeeById(id);

        if (employee == null)
            return "redirect:/employees/list/?employeeNotFound";

        model.addAttribute("employee", employee);
        return "view-employee-details";
    }

}
