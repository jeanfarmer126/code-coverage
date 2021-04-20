# Code Coverage Lab

## Prerequisites

1. Install Java 11
    * Optional: Install Java 11 with a version manager tool like [SDKMAN!](https://sdkman.io/install)
2. Fork this repository and clone it
    ```bash
   git clone https://github.com/your-username/code-coverage
   ```
3. Optional: If you have SDKMAN! installed, you can run `sdk env` at the root of the project to configure the environment

## Running

To run the Spring Boot application, at the root of the project, run
```bash
./mvnw spring-boot:run
```

## Coverage

1. To run unit tests and generate a coverage report with JaCoCo, at the root of the project, run
    ```bash
    ./mvnw clean verify
    ```
   Notice the logs output to the console
   ```
   [INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
   ...
   [INFO] Analyzed bundle 'codecoverage' with 2 classes
   [INFO] All coverage checks have been met.
   ```
   If some of the coverage checks had failed, the build would have failed.
   You can try this out by commenting out some unit tests, e.g., `UnscrableServiceTest#unscramblesMatrix`.
   Moreover, you can leverage these types of checks in code coverage frameworks like JaCoCo to fail your build in a CI pipeline.
   By doing so, you can ensure that the code you commit consistently works as expected under different application states.
2. Find the `target` directory that is generated at the root of the project.
   Expand the `site` directory within the `target` directory.
   JaCoCo generates `jacoco.exec` - a binary file with coverage report data.
   When all the checks have passed, you can open the `index.html` report webpage under `target/site/jacoco`.
   Code coverage reports are a great way to show business and leadership how stable an application's code generally is.
3. Add an additional check for branch coverage.
   There won't be any noticeable change to the coverage results because code coverage is already at 100%.
   <details>
      <summary>Spoiler</summary>
         
      ```xml
      <limit>
         <counter>BRANCH</counter>
         <value>COVEREDRATIO</value>
         <minimum>0.9</minimum>
      </limit>
      ```
   
   </details>
   
## Next Steps

1. To automate the process for generating a code coverage report, integrate this project with a CI pipeline
   * Jenkins
   * GitHub Actions
   * GitLab CI
   * Azure DevOps
   * AWS CodeBuild
2. Implementing code coverage in a project and generating code coverage reports isn't enough to ensure high code quality.
   It's also important to integrate a static code analysis tool like [SonarQube](https://www.sonarqube.org/) to detect bugs, code smells, and security vulnerabilities.
   JaCoCo integrates nicely with SonarQube.
3. In addition to implementing static code analysis tools, you can track metrics for your application with tools such as [Graphite](https://graphiteapp.org/) or [Prometheus](https://prometheus.io/).
   These metrics can help you and your client make informed decisions to improve user experience.