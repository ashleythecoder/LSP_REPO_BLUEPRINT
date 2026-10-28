package org.howard.edu.lsp.midterm.inheritance;

/**
 * Pays a 15 percent premium on every hour worked.
 * @author Ashley Petgrave
 */
public class Contractor extends Employee {
    /**
     * Creates a contractor's weekly pay record.
     * @param hoursWorked nonnegative weekly hours
     * @param hourlyRate nonnegative hourly rate
     */
    public Contractor(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates hourly pay including the contractor premium.
     * @return weekly pay including the 15 percent premium
     */
    @Override
    public double calculatePay() {
        return getHoursWorked() * getHourlyRate() * 1.15;
    }
}
