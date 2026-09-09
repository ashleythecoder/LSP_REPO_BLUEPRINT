package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

/** Reads employee data, calculates payroll, and writes the transformed CSV. */
public class ETLPipeline {
    private static final Path INPUT = Paths.get("data/employees.csv");
    private static final Path OUTPUT = Paths.get("data/transformed_employees.csv");
    private static final BigDecimal FORTY = new BigDecimal("40");
    private static final String HEADER = "EmployeeID,Name,Department,HoursWorked,"
            + "HourlyRate,GrossPay,PayLevel,EmploymentStatus";

    /** Runs the complete ETL process without arguments or keyboard input. */
    public static void main(String[] args) {
        int read = 0;
        int transformed = 0;
        int skipped = 0;

        try (BufferedReader reader = Files.newBufferedReader(INPUT, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(OUTPUT, StandardCharsets.UTF_8)) {
            writer.write(HEADER);
            writer.newLine();
            reader.readLine(); // The first line is the input header.

            String line;
            while ((line = reader.readLine()) != null) {
                read++;
                String result = transform(line);
                if (result == null) {
                    skipped++;
                } else {
                    writer.write(result);
                    writer.newLine();
                    transformed++;
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to complete payroll ETL: " + e.getMessage());
            return;
        }

        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output file: " + OUTPUT);
    }

    /** Returns the output row, or null when the input row must be skipped. */
    private static String transform(String line) {
        if (line.trim().isEmpty()) {
            return null;
        }
        String[] fields = line.split(",", -1);
        if (fields.length != 5) {
            return null;
        }
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        String name = fields[1].toUpperCase(Locale.ROOT);
        String department = fields[2];

        int id;
        BigDecimal hours;
        BigDecimal rate;
        try {
            id = Integer.parseInt(fields[0]);
            hours = new BigDecimal(fields[3]);
            rate = new BigDecimal(fields[4]);
        } catch (NumberFormatException e) {
            return null;
        }
        if (hours.signum() < 0 || rate.signum() < 0) {
            return null;
        }

        BigDecimal pay;
        if (hours.compareTo(FORTY) <= 0) {
            pay = hours.multiply(rate);
        } else {
            BigDecimal overtime = hours.subtract(FORTY).multiply(rate)
                    .multiply(new BigDecimal("1.5"));
            pay = FORTY.multiply(rate).add(overtime);
        }
        if (department.equals("IT")) {
            pay = pay.multiply(new BigDecimal("1.05"));
        }
        pay = pay.setScale(2, RoundingMode.HALF_UP);
        String level = payLevel(pay);
        String status = hours.compareTo(new BigDecimal("30")) < 0
                ? "Part-Time" : "Full-Time";

        return id + "," + name + "," + department + "," + format(hours) + ","
                + format(rate) + "," + format(pay) + "," + level + "," + status;
    }

    private static String payLevel(BigDecimal pay) {
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

    private static String format(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
