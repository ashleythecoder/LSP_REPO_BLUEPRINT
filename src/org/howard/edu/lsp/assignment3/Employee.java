package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Immutable employee data after CSV normalization and validation. */
public final class Employee {
    private final int id;
    private final String name;
    private final String department;
    private final BigDecimal hoursWorked;
    private final BigDecimal hourlyRate;

    /** Creates an employee using the original numeric precision. */
    public Employee(int id, String name, String department,
                    BigDecimal hoursWorked, BigDecimal hourlyRate) {
        if (hoursWorked.signum() < 0 || hourlyRate.signum() < 0) {
            throw new IllegalArgumentException("Hours and rate must not be negative.");
        }
        this.id = id;
        this.name = name;
        this.department = department;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public BigDecimal getHoursWorked() { return hoursWorked; }
    public BigDecimal getHourlyRate() { return hourlyRate; }
}
