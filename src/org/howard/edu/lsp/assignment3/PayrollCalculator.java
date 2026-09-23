package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Applies payroll rules independently of CSV formatting and file access. */
public final class PayrollCalculator {
    private static final BigDecimal REGULAR_HOURS = new BigDecimal("40");
    private static final BigDecimal FULL_TIME_HOURS = new BigDecimal("30");
    private static final BigDecimal OVERTIME_MULTIPLIER = new BigDecimal("1.5");
    private static final BigDecimal IT_MULTIPLIER = new BigDecimal("1.05");

    /** Calculates overtime, bonus, rounded pay, pay level, and status in order. */
    public PayrollResult calculate(Employee employee) {
        BigDecimal hours = employee.getHoursWorked();
        BigDecimal rate = employee.getHourlyRate();
        BigDecimal pay;
        if (hours.compareTo(REGULAR_HOURS) <= 0) {
            pay = hours.multiply(rate);
        } else {
            BigDecimal overtime = hours.subtract(REGULAR_HOURS)
                    .multiply(rate).multiply(OVERTIME_MULTIPLIER);
            pay = REGULAR_HOURS.multiply(rate).add(overtime);
        }
        if (employee.getDepartment().equals("IT")) {
            pay = pay.multiply(IT_MULTIPLIER);
        }
        pay = pay.setScale(2, RoundingMode.HALF_UP);
        String status = hours.compareTo(FULL_TIME_HOURS) < 0
                ? "Part-Time" : "Full-Time";
        return new PayrollResult(employee, pay, payLevel(pay), status);
    }

    private String payLevel(BigDecimal pay) {
        if (pay.compareTo(new BigDecimal("500")) < 0) {
            return "Low";
        }
        if (pay.compareTo(new BigDecimal("1000")) < 0) {
            return "Standard";
        }
        if (pay.compareTo(new BigDecimal("2000")) < 0) {
            return "High";
        }
        return "Executive";
    }
}
