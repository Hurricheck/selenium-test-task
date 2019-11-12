package com.selenium.taf.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    private static final String FIRST_ELEMENT_ID = "a";
    private static final String SECOND_ELEMENT_ID = "b";
    private static final String OPERATOR_ELEMENT_ID = "operator";
    private static final String RESULT_FIELD_ID = "r";
    private static final String SUBMIT_BUTTON_ID = "submit";
    private static final String RULER_MORE_XPATH = "//span[@class = 'more']";
    private static final String RULER_LESS_XPATH = "//span[@class = 'less']";
    private static final String RULER_VALUE_ID = "rulervalue";

    private static final String CORRECT_RESULT_PICTURE_ID = "good";
    private static final String INCORRECT_RESULT_PICTURE_ID = "bad";
    private static final String RULER_START_XPATH = "//div[@class = 'rulerstart']";
    private static final String RULER_CONT_XPATH = "//div[@class = 'rulercont']";

    @FindBy(id = FIRST_ELEMENT_ID)
    private WebElement firstElement;

    @FindBy(id = SECOND_ELEMENT_ID)
    private WebElement secondElement;

    @FindBy(id = OPERATOR_ELEMENT_ID)
    private WebElement operatorElement;

    @FindBy(id = RESULT_FIELD_ID)
    private WebElement inputResult;

    @FindBy(id = SUBMIT_BUTTON_ID)
    private WebElement submitButton;

    @FindBy(xpath = RULER_MORE_XPATH)
    private WebElement rulerMoreButton;

    @FindBy(xpath = RULER_LESS_XPATH)
    private WebElement rulerLessButton;

    @FindBy(id = RULER_VALUE_ID)
    private WebElement rulerValue;

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int calculate() throws Exception {
        int a = Integer.valueOf(firstElement.getAttribute("value"));
        int b = Integer.valueOf(secondElement.getAttribute("value"));
        String operator = operatorElement.getText();
        if (operator.equals("+")) {
            return a+b;
        } else if(operator.equals("-")){
            return a-b;
        } else {
            throw new Exception("invalid operator");
        }
    }

    public void inputResultValue(String result) {
        inputResult.click();
        inputResult.sendKeys(result);
    }

    public void submitResult() {
        submitButton.click();
    }

    public boolean checkIfCorrectPictureAppeared(){
        return checkIfElementByIDIsDisplayed(CORRECT_RESULT_PICTURE_ID);
    }

    public boolean checkIfIncorrectPictureAppeared(){
        return checkIfElementByIDIsDisplayed(INCORRECT_RESULT_PICTURE_ID);
    }

    public boolean checkIfElementByIDIsDisplayed(String id) {
        WebElement webElement = driver.findElement(By.id(id));
        return webElement.isDisplayed();
    }

    public void clickRulerLess() {
        rulerLessButton.click();
    }

    public void clickRulerMore() {
        rulerMoreButton.click();
    }

    public int getRulerValue() {
        return Integer.valueOf(rulerValue.getText());
    }

    public int getRulerLength() {
        List<WebElement> rulerConts = driver.findElements(By.xpath(RULER_CONT_XPATH));
        if (rulerConts!=null) {
            return rulerConts.size() + 1;
        } else {
            return 1;
        }
    }

    public void clickRulerButton(String buttonName) throws Exception {
        if (buttonName.equals("more")) {
            clickRulerMore();
        } else if (buttonName.equals("less")) {
            clickRulerLess();
        } else {
            throw new Exception("No such ruler button " + buttonName);
        }
    }
}
