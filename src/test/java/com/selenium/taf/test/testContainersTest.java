package com.selenium.taf.test;

import com.selenium.taf.factory.WebDriverFactory;
import com.selenium.taf.page.MainPage;
import com.selenium.taf.util.TestUtil;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.testcontainers.containers.NginxContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

public class testContainersTest {
    private static final String BROWSER_NAME = "google_chrome";
    private final static String HTML_PROPERTY_VALUE = "path.html-content";

    private WebDriver driver;
    @Rule
    public NginxContainer nginxContainer = new NginxContainer<>()
            .withCustomContent(TestUtil.getPropertyValue(HTML_PROPERTY_VALUE))
            .waitingFor(new HttpWaitStrategy());

    @Before
    public void setUp () {
        //nginxContainer.start();
    }

    @Test
    public void smoke() throws Exception {
        driver = WebDriverFactory.getWebDriver(BROWSER_NAME);
        driver.get(nginxContainer.getBaseUrl("http", 80).toString());
        MainPage mainPage = new MainPage(driver);
        int correctResult = mainPage.calculate();
        mainPage.inputResultValue(String.valueOf(correctResult));
        mainPage.submitResult();
        Assert.assertTrue(mainPage.checkIfCorrectPictureAppeared());
    }

    @After
    public void shutDownDriver() {
        driver.close();
    }
}
