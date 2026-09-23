# Assignment 3: Object-Oriented Refactoring

## Comparison with Assignment 2

Assignment 2 used one `ETLPipeline` class with static methods. Its `main`
method handled file access and counting, while `transform` combined parsing,
validation, payroll calculations, and CSV output construction. Employee data
existed only as local variables and array elements.

Assignment 3 separates those responsibilities into five classes:

| Class | Responsibility |
| --- | --- |
| `Employee` | Encapsulates validated employee fields in private, final fields with getters. Preserves the original hours and rate precision. |
| `PayrollResult` | Associates an employee with the calculated gross pay, pay level, and employment status. Its fields are also private and final. |
| `PayrollCalculator` | Applies overtime, the case-sensitive IT bonus, half-up rounding, pay levels, and employment status without depending on files or CSV. |
| `EmployeeCsv` | Normalizes and validates input rows and formats results as CSV. Returns an empty `Optional` for rows that must be skipped. |
| `ETLPipeline` | Owns the CSV converter and calculator, streams the files, counts rows, and prints the summary. Its `main` starts the complete process. |

The pipeline uses composition: it delegates conversion and calculation to its
collaborators. A `PayrollResult` contains the corresponding `Employee`, so the
relationship between source data and calculated values is explicit. Inheritance
is unnecessary because none of these responsibilities is a specialized version
of another.

This improves separation of concerns and encapsulation. Payroll rules can be
tested with objects without opening files, and CSV formatting can change without
changing the payroll rules. There are more files to navigate, but each class has
a focused purpose. The redesign preserves the relative paths, validation rules,
numeric precision, output order, and console summary from Assignment 2.

## Compile and run

From the repository root (with a JDK installed):

```sh
javac -d /tmp/lsp-assignment3-classes src/org/howard/edu/lsp/assignment3/*.java
java -cp /tmp/lsp-assignment3-classes org.howard.edu.lsp.assignment3.ETLPipeline
```

Input: `data/employees.csv`. Output: `data/transformed_employees.csv`.
The supplied grading data produces 14 rows read, 7 transformed, and 7 skipped.
Compiled files should remain outside the repository.

## AI and external resources

Neither AI or Internet resources were used in the creation of this assignment. 
