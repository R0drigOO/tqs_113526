# 8.1 Local analysis

Token: 'Analyze "Euromillion"'

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=Euromillion \
  -Dsonar.projectName='Euromillion' \
  -Dsonar.host.url=http://127.0.0.1:9000 \
  -Dsonar.token=sqp_32197fefed7f437a56fb340c64453cbe0137bba5

### ------------------------------------------------------------------------------------------

f) In general the code pass the quality gate because it has 0 security and reliability issues, 1 security hotspot with 0% duplications, however, it has 36 maintainability issues which most of them don't greatly affect the quality but some (3) of them can cause a infinite loop iteraction.
The coverage is also ok/good, has Dip.java, BoundedSetOfNaturals.java and EuromillionsDraw.java have 80+% of coverage but Dip.java has 8 uncovered conditions which is a bit high and CuponEuromillions.java only have 35% coverage with 2 uncovered conditions

### ------------------------------------------------------------------------------------------

g) Issue | Problem | description | How to solve
Security:           - None

Reliability:        - None

Maintainability:    - Problem: "Preconditions" and logging arguments should not require evaluationjava:S2629
                    - Description: Some method calls can effectively be "no-ops", meaning that the invoked method does nothing, based on the application’s configuration (eg: debug logs in production). However, even if the method effectively does nothing, its arguments may still need to evaluated before the method is called. Passing message arguments that require further evaluation into a Guava com.google.common.base.Preconditions check can result in a performance penalty. That is because whether or not they’re needed, each argument must be resolved before the method is actually called. Similarly, passing concatenated strings into a logging method can also incur a needless performance hit because the concatenation will be performed every time the method is called, whether or not the log level is low enough to show the message. Instead, you should structure your code to pass static or pre-computed values into Preconditions conditions check and logging calls. Specifically, the built-in string formatting should be used instead of string concatenation, and if the message is the result of a method call, then Preconditions should be skipped altogether, and the relevant exception should be conditionally thrown instead.
                    - How to fix: Invoke method(s) only conditionally.

                    - Problem: Unnecessary imports should be removedjava:S1128
                    - Description: Unnecessary imports refer to importing types that are not used or referenced anywhere in the code. Although they don’t affect the runtime behavior of the application after compilation, removing them will: Improve the readability and maintainability of the code; Help avoid potential naming conflicts; Improve the build time, as the compiler has fewer lines to read and fewer types to resolve; Reduce the number of items the code editor will show for auto-completion, thereby showing fewer irrelevant suggestions.
                    . How to fix: While it’s not difficult to remove these unneeded lines manually, modern code editors support the removal of every unnecessary import with a single click from every file of the project.

                    - Problem: "for" loop stop conditions should be invariantjava:S127
                    - Description: A for loop termination condition should test the loop counter against an invariant value that does not change during the execution of the loop. Invariant termination conditions make the program logic easier to understand and maintain. This rule tracks three types of non-invariant termination conditions: When the loop counters are updated in the body of the for loop; When the termination condition depends on a method call; When the termination condition depends on an object property since such properties could change during the execution of the loop
                    . How to fix: Make the termination condition invariant by using a constant or a local variable instead of an expression that could change during the execution of the loop.

Security hotspot:   - Problem: Using pseudorandom number generators (PRNGs) is security-sensitivejava:S2245
                    - Description: PRNGs are algorithms that produce sequences of numbers that only approximate true randomness. While they are suitable for applications like simulations or modeling, they are not appropriate for security-sensitive contexts because their outputs can be predictable if the internal state is known. In contrast, cryptographically secure pseudorandom number generators (CSPRNGs) are designed to be secure against prediction attacks. CSPRNGs use cryptographic algorithms to ensure that the generated sequences are not only random but also unpredictable, even if part of the sequence or the internal state becomes known. This unpredictability is crucial for security-related tasks such as generating encryption keys, tokens, or any other values that must remain confidential and resistant to guessing attacks.
                    - How to fix: Use a cryptographically secure pseudo random number generator (CSPRNG) like "java.security.SecureRandom" in place of a non-cryptographic PRNG. Use the generated random values only once. You should not expose the generated random value. If you have to store it, make sure that the database or file is secure.

### ------------------------------------------------------------------------------------------

h) Checkstyle, PMD, and SpotBugs are static analysis tools for Java that help to ensure code quality and maintainability. Checkstyle helps with formatting and coding conventions, PMD flags common code "bad smells" and performance issues, and SpotBugs inspects compiled bytecode for potential bugs or vulnerabilities. They help ensure code quality and maintainability.