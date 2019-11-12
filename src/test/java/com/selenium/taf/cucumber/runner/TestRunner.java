package com.selenium.taf.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml", "json:target/Destination/cucumber.json"},
        features = "src/main/resources/features",
        glue = {"com.selenium.taf.cucumber.stepdefs"})
public class TestRunner {
}
