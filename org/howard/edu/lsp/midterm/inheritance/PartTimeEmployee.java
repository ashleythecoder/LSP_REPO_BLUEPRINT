package org.howard.edu.lsp.midterm.inheritance;

/**
 * Pays the straight hourly rate for every hour worked.
 * @author Ashley Petgrave
 */
public class PartTimeEmployee extends Employee {
    /**
     * Creates a part-time employee's weekly pay record.
     * @param hoursWorked nonnegative weekly hours
     * @param hourlyRate nonnegative hourly rate
     */
    public PartTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates straight hourly pay without an overtime premium.
     * @return weekly pay
     */
    @Override
    public double calculatePay() { return getHoursWorked() * getHourlyRate(); }
}
