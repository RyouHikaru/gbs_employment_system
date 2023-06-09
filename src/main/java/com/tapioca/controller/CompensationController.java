package com.tapioca.controller;

import com.tapioca.entity.Compensation;
import com.tapioca.entity.Employee;
import com.tapioca.service.CompensationService;
import com.tapioca.service.EmployeeService;
import com.tapioca.utils.CompensationSearchCriteria;
import com.tapioca.utils.CustomDateFormatter;
import com.tapioca.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@Controller
public class CompensationController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompensationService compensationService;

    @GetMapping("/compensation/**")
    public String redirectToHome() {
        return "redirect:/";
    }

    @GetMapping("/compensation/new")
    public String displayAddCompensationPage(Model model) {
        model.addAttribute("compensation", new Compensation());
        return "add-compensation";
    }

    @PostMapping("/compensation/save")
    public String createOrUpdateCompensation(@Valid @ModelAttribute("compensation") Compensation compensation, BindingResult result, Model model) {
        String URL = "add-compensation";
        String redirectURL = "redirect:/compensation/new?success";

        if (compensation.getId() != null) {
            URL = "edit-compensation";
            redirectURL = "redirect:/compensation/edit/" + compensation.getEmployee().getId() + "/" + compensation.getId() + "?success";
        }

        if (compensation.getEmployee() == null) {
            model.addAttribute("errorMessage", ErrorMessage.INVALID_EMPLOYEE_ID.getMessage());
            return URL;
        }

        String[] fieldsToCheck = {"type", "amount", "date"};
        for (String field : fieldsToCheck) {
            if (result.hasFieldErrors(field)) {
                model.addAttribute("errorMessage", result.getFieldError(field).getDefaultMessage());
                return URL;
            }
        }

        String errorMessage = getValidationResult(compensation);

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return URL;
        }

        compensationService.save(compensation);
        return redirectURL;
    }

    @GetMapping("/compensation/search/{employeeId}")
    public String displayCompensationHistorySearchPage(@PathVariable("employeeId") Long employeeId, Model model) {
        Employee employee = employeeService.retrieveEmployeeById(employeeId);

        if (employee == null)
            return "redirect:/employees/list?employeeNotFound";

        model.addAttribute("employeeId", employeeId);
        return "search-compensation";
    }

    @PostMapping("/compensation/search/{employeeId}")
    public String displayCompensationHistoryPage(@ModelAttribute("compensationSearch") CompensationSearchCriteria criteria, Model model) {
        String errorMessage = getSearchCriteriaValidationResult(criteria);
        model.addAttribute("employeeId", criteria.getEmployeeId());

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "search-compensation";
        }

        model.addAttribute("monthlyCompensations", compensationService.getTotalAmountPerMonthByCriteria(criteria));
        return "view-compensation-history";
    }

    @GetMapping("/compensation/search/breakdown")
    public String displayCompensationBreakdownPage(@RequestParam(value = "employeeId", required = false) Long employeeId,
                                               @RequestParam(value = "year", required = false) Integer year,
                                               @RequestParam(value = "month", required = false) String month,
                                               Model model) {

        if (employeeId == null || year == null || month == null)
            return "redirect:/";

        LocalDate date = LocalDate.of(year, Month.valueOf(month.toUpperCase()), 1);

        model.addAttribute("compensations", compensationService.getCompensationsByDate(employeeId, date));
        return "view-compensation-breakdown";
    }

    @GetMapping("/compensation/edit/{employeeId}/{compensationId}")
    public String displayCompensationDetailsPage(@PathVariable("employeeId") Long employeeId,
                                                 @PathVariable("compensationId") Long compensationId,
                                                 Model model) {
        Employee employee = employeeService.retrieveEmployeeById(employeeId);

        if (employee == null)
            return "redirect:/employees/list?employeeNotFound";

        Compensation compensation = compensationService.getCompensationById(compensationId);

        if (compensation == null)
            return "redirect:/";

        model.addAttribute("compensation", compensation);
        return "edit-compensation";
    }

    /* Compensation-specific methods */

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new CustomDateFormatter());
    }

    public String getValidationResult(Compensation compensation) {
        switch (compensation.getType()) {
            case SALARY -> {
                if (compensation.getId() == null && compensationService.hasDuplicateSalaryEntry(compensation))
                    return ErrorMessage.INVALID_SALARY.getMessage();
            }
            case BONUS, COMMISSION, ALLOWANCE -> {
                if (compensation.getAmount().compareTo(BigDecimal.ZERO) <= 0)
                    return ErrorMessage.INVALID_AMOUNT.getMessage();
                else if (compensation.getDescription().isEmpty())
                    return ErrorMessage.MISSING_DESCRIPTION.getMessage();
            }
            case ADJUSTMENT -> {
                if (compensation.getAmount().compareTo(BigDecimal.ZERO) == 0)
                    return ErrorMessage.AMOUNT_IS_ZERO.getMessage();
                else if (compensation.getDescription().isEmpty())
                    return ErrorMessage.MISSING_DESCRIPTION.getMessage();
            }
        }

        return null;
    }

    public String getSearchCriteriaValidationResult(CompensationSearchCriteria criteria) {
        if (criteria.getStartDate() == null)
            return ErrorMessage.NULL_START_DATE.getMessage();
        if (criteria.getEndDate() == null)
            return ErrorMessage.NULL_END_DATE.getMessage();
        if (criteria.getStartDate().isAfter(criteria.getEndDate()))
            return ErrorMessage.INVALID_START_DATE.getMessage();
        return null;
    }
}