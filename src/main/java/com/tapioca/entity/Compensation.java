package com.tapioca.entity;

import com.tapioca.utils.CompensationType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "compensation")
public class Compensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Compensation type is required")
    private CompensationType type;

    @Column(nullable = false)
    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @Column
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Date is required")
    private LocalDate date;

    @ManyToOne(targetEntity = Employee.class)
    private Employee employee;

    public Compensation() {
    }

    public Compensation(Long id, CompensationType type, BigDecimal amount, String description, LocalDate date, Employee employee) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompensationType getType() {
        return type;
    }

    public void setType(CompensationType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Compensation{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", employee=" + employee +
                '}';
    }
}
