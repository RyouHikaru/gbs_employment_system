package com.tapioca.controller;

import com.tapioca.entity.Compensation;
import com.tapioca.service.CompensationService;
import com.tapioca.utils.CustomDateFormatter;
import com.tapioca.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class CompensationController {

    @Autowired
    private CompensationService compensationService;

    @GetMapping("/compensation")
    public String displayAddCompensationPage(Model model) {
        model.addAttribute("compensation", new Compensation());
        return "add-compensation";
    }

    @PostMapping("/compensation/save")
    public String createOrUpdateCompensation(@Valid @ModelAttribute("compensation") Compensation compensation, BindingResult result, Model model) {
        if (compensation.getEmployee() == null) {
            model.addAttribute("errorMessage", ErrorMessage.INVALID_EMPLOYEE_ID.getMessage());
            return "add-compensation";
        }

        String[] fieldsToCheck = {"type", "amount", "date"};
        for (String field : fieldsToCheck) {
            if (result.hasFieldErrors(field)) {
                model.addAttribute("errorMessage", result.getFieldError(field).getDefaultMessage());
                return "add-compensation";
            }
        }

        String errorMessage = getValidationResult(compensation);

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "add-compensation";
        }

        compensationService.save(compensation);
        return "redirect:/compensation/?success";
    }

    /* Compensation-specific methods */

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new CustomDateFormatter());
    }

    public String getValidationResult(Compensation compensation) {
        switch (compensation.getType()) {
            case SALARY -> {
                if (compensationService.hasDuplicateSalaryEntry(compensation))
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

}
