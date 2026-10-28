# Course registration CRC cards

The initial design preserves the given calculation: charged credit hours times 750, reduced by 10 percent when the charged hours are at least 12. The caller supplies the charged hours explicitly; a change to semester-wide pricing would require a separate rule for determining that total. Domain fields are private, required identifiers are validated, and charged hours cannot be negative.

Class: Student

Responsibilities:
- Maintain student identity, name, and email address with controlled access.
- Validate required identity and contact information.

Collaborators: None.

Class: Course

Responsibilities:
- Maintain course code and credit hours.
- Validate the course code and credit hours.

Collaborators: None.

Class: Registration

Responsibilities:
- Represent a student's registration for a course.
- Retain the charged hours and calculated tuition as an immutable registration record.
- Provide the details needed for storage and confirmation.

Collaborators: Student, Course.

Class: TuitionCalculator

Responsibilities:
- Calculate tuition from charged hours using the rate and discount rules.
- Use decimal monetary values and round the final charge to cents.

Collaborators: None; receives charged hours as an input.

Class: RegistrationService

Responsibilities:
- Coordinate a registration request using validated Student and Course objects.
- Ask TuitionCalculator for tuition and create a Registration.
- Save through RegistrationStore before requesting a confirmation.
- Return a result distinguishing save failure, saved registration, and notification failure; log the actual outcome.

Collaborators: Student, Course, Registration, TuitionCalculator, RegistrationStore, ConfirmationSender, RegistrationLogger.

Class: RegistrationStore (interface)

Responsibilities:
- Define an operation for saving a Registration.
- Report whether persistence succeeded without exposing storage details.

Collaborators: Registration.

Class: FileRegistrationStore (implements RegistrationStore)

Responsibilities:
- Append registration records to a configured local file.
- Escape serialized fields, close resources reliably, and report storage failures.

Collaborators: Registration.

Class: ConfirmationSender (interface)

Responsibilities:
- Define an operation for sending a saved registration confirmation to a Student.
- Report delivery-request success or failure without claiming guaranteed receipt.

Collaborators: Registration, Student.

Class: EmailConfirmationSender (implements ConfirmationSender)

Responsibilities:
- Format an email confirmation and submit it to an email delivery service.
- Validate the email destination and report delivery-request failures.

Collaborators: Registration, Student, email delivery service.

Class: RegistrationLogger

Responsibilities:
- Record timestamped registration and notification outcomes.
- Record failures without turning them into success messages.

Collaborators: Registration.

Class: RegistrationView

Responsibilities:
- Accept user input and request registration through RegistrationService.
- Display registration details or errors based on the returned outcome.

Collaborators: Student, Course, RegistrationService, Registration.

Application setup supplies the store and sender to RegistrationService. The initial deployment uses FileRegistrationStore and EmailConfirmationSender. The domain classes do not depend on RegistrationView or print directly to the console.
