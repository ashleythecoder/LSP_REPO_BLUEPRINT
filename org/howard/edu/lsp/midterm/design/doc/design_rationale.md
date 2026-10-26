# Adapting storage and confirmations

RegistrationService already depends on RegistrationStore and ConfirmationSender, so its workflow does not need a conditional for every storage or notification option. Application setup chooses implementations from deployment configuration and supplies them to the service. The existing file and email implementations remain available.

## CRC card additions and changes

Class: DatabaseRegistrationStore (implements RegistrationStore)

Responsibilities:
- Save a Registration using parameterized database operations.
- Commit or roll back the storage transaction and report failure through the existing storage contract.

Collaborators: Registration, database connection provider.

Class: TextMessageConfirmationSender (implements ConfirmationSender)

Responsibilities:
- Format a text confirmation for a saved registration.
- Validate the student's phone destination, submit the message through a text delivery service, and report delivery-request failure.

Collaborators: Registration, Student, text delivery service.

Class: Student (updated)

Responsibilities:
- Retain its existing identity and email responsibilities.
- Maintain an optional phone number through controlled access; the selected sender requires an appropriate destination before attempting delivery.

Collaborators: None.

Class: ApplicationSetup

Responsibilities:
- Read deployment settings and construct either FileRegistrationStore or DatabaseRegistrationStore.
- Construct either EmailConfirmationSender or TextMessageConfirmationSender.
- Supply these dependencies to RegistrationService and reject unsupported configuration at startup.

Collaborators: RegistrationService, FileRegistrationStore, DatabaseRegistrationStore, EmailConfirmationSender, TextMessageConfirmationSender.

The existing RegistrationStore and ConfirmationSender contracts stay the same. RegistrationService still calculates, creates, saves, and then requests confirmation, so changing deployment options does not change tuition or registration logic. A save failure prevents a confirmation request. If notification fails after saving, the result identifies that separate failure; retrying notification uses the saved registration rather than repeating the registration.

## Responsibility ownership

TuitionCalculator should own the rate, discount threshold, and discount calculation. These rules describe tuition, not how a record is saved or how a message is delivered. Putting them in FileRegistrationStore or EmailConfirmationSender would duplicate business rules when database and text options are added. Keeping RegistrationService focused on sequencing and TuitionCalculator focused on pricing preserves cohesion and lets pricing be tested without files, databases, or messaging services.
