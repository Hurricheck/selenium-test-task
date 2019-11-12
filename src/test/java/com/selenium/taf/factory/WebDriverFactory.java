package com.selenium.taf.factory;

import com.selenium.taf.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final String CHROME_PROPERTY = "webdriver.chrome.driver";

    private static WebDriver driver;

    public static WebDriver getWebDriver(String browser) {
        if ("google_chrome".equals(browser)) {
            System.setProperty(CHROME_PROPERTY, TestUtil.getPropertyValue("path.chromedriver"));
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--window-size=1280,800","--headless","--no-sandbox", "--single-process", "--disable-gpu");
            driver = new ChromeDriver(chromeOptions);
        } else if ("firefox".equals(browser)) {
        	System.setProperty("webdriver.gecko.driver", TestUtil.getPropertyValue("path.geckodriver"));
        	File pathToBinary = new File(TestUtil.getPropertyValue("path.mozilla"));
        	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        	FirefoxProfile firefoxProfile = new FirefoxProfile();
        	firefoxProfile.setPreference("security.sandbox.content.level", 5);
        	FirefoxOptions ffOptions = new FirefoxOptions();
        	ffOptions.setBinary(ffBinary);
        	ffOptions.setProfile(firefoxProfile);
        	FirefoxDriver _driver = new FirefoxDriver(ffOptions);
        	
            driver = _driver;
        }
        applyConfigs();
        return driver;
    }

    public static WebDriver getWebDriver(String url, DesiredCapabilities capabilities) {
        try {
            driver = new RemoteWebDriver(new java.net.URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        applyConfigs();
        return driver;
    }

    private static void applyConfigs() {
        driver.manage().timeouts().implicitlyWait(Long.valueOf(TestUtil.getPropertyValue("waiting.implicitwait")), TimeUnit.SECONDS);
    }
}
