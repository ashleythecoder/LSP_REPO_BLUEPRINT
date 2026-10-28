package org.howard.edu.lsp.midterm.inheritance;

/**
 * Pays straight time through 40 hours and time-and-a-half beyond 40.
 * @author Ashley Petgrave
 */
public class FullTimeEmployee extends Employee {
    /**
     * Creates a full-time employee's weekly pay record.
     * @param hoursWorked nonnegative weekly hours
     * @param hourlyRate nonnegative hourly rate
     */
    public FullTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates regular pay plus any overtime pay.
     * @return weekly pay including overtime
     */
    @Override
    public double calculatePay() {
        double regular = Math.min(getHoursWorked(), 40);
        double overtime = Math.max(getHoursWorked() - 40, 0);
        return regular * getHourlyRate() + overtime * getHourlyRate() * 1.5;
    }
}
