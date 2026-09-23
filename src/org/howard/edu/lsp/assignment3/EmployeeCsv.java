package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Optional;

/** Converts between payroll objects and the assignment's simple CSV format. */
public final class EmployeeCsv {
    public static final String HEADER = "EmployeeID,Name,Department,HoursWorked,"
            + "HourlyRate,GrossPay,PayLevel,EmploymentStatus";

    /** Normalizes and validates one input row; invalid rows return empty. */
    public Optional<Employee> parse(String line) {
        if (line.trim().isEmpty()) {
            return Optional.empty();
        }
        String[] fields = line.split(",", -1);
        if (fields.length != 5) {
            return Optional.empty();
        }
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        String name = fields[1].toUpperCase(Locale.ROOT);
        try {
            int id = Integer.parseInt(fields[0]);
            BigDecimal hours = new BigDecimal(fields[3]);
            BigDecimal rate = new BigDecimal(fields[4]);
            if (hours.signum() < 0 || rate.signum() < 0) {
                return Optional.empty();
            }
            return Optional.of(new Employee(id, name, fields[2], hours, rate));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /** Formats numeric values only after payroll calculations are complete. */
    public String format(PayrollResult result) {
        Employee employee = result.getEmployee();
        return employee.getId() + "," + employee.getName() + ","
                + employee.getDepartment() + "," + decimal(employee.getHoursWorked())
                + "," + decimal(employee.getHourlyRate()) + ","
                + decimal(result.getGrossPay()) + "," + result.getPayLevel()
                + "," + result.getEmploymentStatus();
    }

    private String decimal(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
