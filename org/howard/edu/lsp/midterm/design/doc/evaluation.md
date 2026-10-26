# Course registration design evaluation

CourseRegistration combines student information, course information, tuition rules, display, storage, notification, and logging. These responsibilities change for different reasons, so keeping them in one class makes maintenance and testing difficult.

## Encapsulation

The five public fields conflict with Riel's heuristic 2.1 that data should be hidden within its class. Any caller can assign negative credit hours, erase the student identifier, or change the course after other fields are populated. Private fields and validated constructors or domain operations would protect valid state. Student and Course should each own their information rather than exposing unrelated mutable fields through one registration object.

## Responsibilities and cohesion

Riel's heuristics 3.1 and 3.2 favor distributing behavior across appropriate classes and avoiding a class that controls too much. registerStudent() calculates tuition, prints output, writes a file, claims to send email, and logs a timestamp. A tuition-rate change, file-format change, or notification change therefore requires editing the same method. A small registration service should coordinate collaborators, with calculation, persistence, notification, and presentation owned by focused classes.

## Model and presentation coupling

Riel's heuristic 3.5 separates the object model from the user interface. Direct System.out calls tie registration behavior to console presentation. A web application could not reuse the operation without also producing console output, and tests must capture output to inspect results. Returning a registration result lets a console or web interface decide how to display it.

## Correctness and failure handling

The method prints a confirmation before saving. If FileWriter fails, the broad exception handler prints a stack trace but execution continues to the apparent email and success log. The caller receives no result distinguishing failure from success. The email step only prints a message; it does not deliver email. Storage errors should propagate as an explicit failure, and notification should occur only after a successful save. A later notification failure should be reported separately from a successful registration so a retry does not create another registration.

## Extensibility and data integrity

The hard-coded filename, rate of 750, discount threshold of 12, and discount multiplier of 0.90 mix deployment settings with business rules. File resources may remain open if writing throws before close(), and unescaped comma-separated fields can corrupt records. A storage class can manage resource cleanup and serialization, while TuitionCalculator owns the pricing rules. Monetary calculations should use decimal amounts and a defined rounding rule. The meaning of creditHours also needs clarification: the current calculation discounts at 12 hours, but a true course-load discount would require the student's total registered load if this field describes only one course.

Reference: Arthur J. Riel, *Object-Oriented Design Heuristics*, Addison-Wesley, 1996, Chapters 2 and 3.
