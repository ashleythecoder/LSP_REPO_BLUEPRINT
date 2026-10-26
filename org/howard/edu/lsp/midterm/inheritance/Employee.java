package org.howard.edu.lsp.midterm.inheritance;

/**
 * Defines common weekly work information and a polymorphic pay operation.
 * @author Ashley Petgrave
 */
public abstract class Employee {
    private final double hoursWorked;
    private final double hourlyRate;

    /**
     * Initializes common work information.
     * @param hoursWorked nonnegative weekly hours
     * @param hourlyRate nonnegative hourly rate
     */
    protected Employee(double hoursWorked, double hourlyRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    /**
     * Returns weekly hours.
     * @return hours worked
     */
    protected double getHoursWorked() { return hoursWorked; }

    /**
     * Returns the hourly rate.
     * @return hourly pay rate
     */
    protected double getHourlyRate() { return hourlyRate; }

    /**
     * Calculates weekly pay using the employee's pay rules.
     * @return weekly pay
     */
    public abstract double calculatePay();
}
