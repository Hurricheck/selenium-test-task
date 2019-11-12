# selenium-test-task
Selenium TAF sample.
Currenty configured for Linux+Chrome only.

# How to run:
"docker-compose up" to run tests dockerised

mvn test -Dcucumber.options="src/main/resources/features --tags @selenium"

./run_maven_tests.sh
