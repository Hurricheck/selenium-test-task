# selenium-test-task
Selenium TAF sample.
Currenty configured for Linux+Chrome only with headless mode.

In order to disable headless, remove the webdriver.headless property at .properties file.

However, this may cause TAF crash when run via docker-compose.

# How to run:
"docker-compose up" to run tests dockerised

mvn test -Dcucumber.options="src/main/resources/features --tags @selenium"

./run_maven_tests.sh
