package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Immutable payroll result associated with its source employee. */
public final class PayrollResult {
    private final Employee employee;
    private final BigDecimal grossPay;
    private final String payLevel;
    private final String employmentStatus;

    public PayrollResult(Employee employee, BigDecimal grossPay,
                         String payLevel, String employmentStatus) {
        this.employee = employee;
        this.grossPay = grossPay;
        this.payLevel = payLevel;
        this.employmentStatus = employmentStatus;
    }

    public Employee getEmployee() { return employee; }
    public BigDecimal getGrossPay() { return grossPay; }
    public String getPayLevel() { return payLevel; }
    public String getEmploymentStatus() { return employmentStatus; }
}
