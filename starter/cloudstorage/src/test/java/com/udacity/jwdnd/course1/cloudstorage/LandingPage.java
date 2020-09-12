package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

    @FindBy(id = "signupButton")
    private WebElement signupButton;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void gotoSignup(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(this.signupButton)).click();
    }

    public void gotoLogin(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(this.loginButton)).click();
    }
}
