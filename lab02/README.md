mvn test:
- Executes the unit tests (using the Surefire plugin).
- Skips any integration tests (Failsafe plugin) by default.

mvn package:
- Compiles code and runs unit tests.
- Packages the compiled code (e.g., JAR or WAR).
- Integration tests are not automatically run unless specifically configured.

mvn package -DskipTests=true:
- Skips all tests (unit and integration).
- Packages the code without running any tests.

mvn failsafe:integration-test:
- Runs only the integration tests configured through Failsafe.
- Does not produce a final package or install artifacts to the local repo by itself.

mvn install:
- Compiles code, runs unit tests, packages the code, runs integration tests (if configured in the verify phase),
- Installs the final artifact into the local Maven repository.