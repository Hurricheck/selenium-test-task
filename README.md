# selenium-test-task
Selenium TAF sample.
Currenty configured for Linux+Chrome only.

How to run: \n
-docker-compose up to run tests dockerised \n
-mvn test -Dcucumber.options="src/main/resources/features --tags @selenium" \n
-./run_maven_tests.sh \n
