# Assignment 2: Employee Payroll ETL

Run from the repository root with Java 11 or newer:

```sh
java src/org/howard/edu/lsp/assignment2/ETLPipeline.java
```

Reads `data/employees.csv` and writes `data/transformed_employees.csv`.
Uses only the Java standard library. BigDecimal preserves the input precision
until gross pay is rounded half-up. Malformed rows are skipped and counted.

AI disclosure: OpenAI Codex generated the implementation and assisted with
verification against the assignment's grading dataset and additional edge cases.
No external Internet sources were used.
