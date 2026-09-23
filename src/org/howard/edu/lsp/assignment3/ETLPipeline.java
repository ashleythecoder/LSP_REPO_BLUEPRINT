package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/** Coordinates extraction, transformation, loading, and the run summary. */
public final class ETLPipeline {
    private final EmployeeCsv csv;
    private final PayrollCalculator calculator;

    /** Composes the collaborators used by this pipeline. */
    public ETLPipeline() {
        csv = new EmployeeCsv();
        calculator = new PayrollCalculator();
    }

    /** Runs the assignment using the required relative paths and no arguments. */
    public static void main(String[] args) {
        new ETLPipeline().run(Paths.get("data/employees.csv"),
                Paths.get("data/transformed_employees.csv"));
    }

    /** Streams input rows, skips invalid records, and reports completed output. */
    public void run(Path input, Path output) {
        int read = 0;
        int transformed = 0;
        int skipped = 0;
        try (BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {
            writer.write(EmployeeCsv.HEADER);
            writer.newLine();
            reader.readLine(); // Do not transform the input header.
            String line;
            while ((line = reader.readLine()) != null) {
                read++;
                Optional<Employee> employee = csv.parse(line);
                if (!employee.isPresent()) {
                    skipped++;
                    continue;
                }
                PayrollResult result = calculator.calculate(employee.get());
                writer.write(csv.format(result));
                writer.newLine();
                transformed++;
            }
        } catch (IOException e) {
            System.err.println("Unable to complete payroll ETL: " + e.getMessage());
            return;
        }
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output file: " + output);
    }
}
