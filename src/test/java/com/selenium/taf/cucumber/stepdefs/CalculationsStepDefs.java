package com.selenium.taf.cucumber.stepdefs;

import com.selenium.taf.factory.WebDriverFactory;
import com.selenium.taf.page.MainPage;
import com.selenium.taf.util.TestUtil;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testcontainers.containers.NginxContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.containers.wait.strategy.Wait;

public class CalculationsStepDefs implements En {
    private static final String BROWSER_NAME = "google_chrome";
    private final static String HTML_PROPERTY_VALUE = "path.html-content";

    private WebDriver driver;
    private MainPage mainPage;

    private NginxContainer nginxContainer;

    public CalculationsStepDefs() {
        Given("^page is opened$", () -> {
            nginxContainer = new NginxContainer<>()
                    .withCustomContent(TestUtil.getPropertyValue(HTML_PROPERTY_VALUE))
                    .waitingFor(new HttpWaitStrategy())
                    .waitingFor(Wait.forHttp("/"));
            nginxContainer.start();
            driver = WebDriverFactory.getWebDriver(BROWSER_NAME);
            driver.get(nginxContainer.getBaseUrl("http", 80).toString());
            mainPage = new MainPage(driver);
        });
        When("^correct answer has been input$", () -> {
            int correctResult = mainPage.calculate();
            mainPage.inputResultValue(String.valueOf(correctResult));
        });
        When("^incorrect answer has been input$", () -> {
            int correctResult = mainPage.calculate();
            mainPage.inputResultValue(String.valueOf(correctResult-1));
        });
        And("^the result was submitted$", () -> {
            mainPage.submitResult();
        });
        After (new String[]{"@avast"}, 0, 1, (Scenario scenario) -> {
            driver.close();
            mainPage = null;

        });
        When("^\"([^\"]*)\" has been input as answer$", (String incorrectValue) -> {
            mainPage.inputResultValue(incorrectValue);
        });
        Then("^the correct image was shown$", () -> {
            Assert.assertTrue(mainPage.checkIfCorrectPictureAppeared());
        });
        Then("^the incorrect image was shown$", () -> {
            Assert.assertTrue(mainPage.checkIfIncorrectPictureAppeared());
        });
        When("^ruler \"([^\"]*)\" button is clicked$", (String buttonName) -> {
            mainPage.clickRulerButton(buttonName);
        });
        Then("^ruler value is \"([^\"]*)\"$", (String expected) -> {
            Assert.assertEquals((int)Integer.valueOf(expected), mainPage.getRulerValue());
        });
        And("^the ruler length is \"([^\"]*)\"$", (String expected) -> {
            Assert.assertEquals((int)Integer.valueOf(expected), mainPage.getRulerLength());
        });
        When("^ruler \"([^\"]*)\" button is clicked \"([^\"]*)\" times$", (String buttonName, String clicksAmount) -> {
            for (int i = 0; i<Integer.valueOf(clicksAmount); i++) {
                mainPage.clickRulerButton(buttonName);
            }
        });
    }
}
