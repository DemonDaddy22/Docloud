package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    @FindBy(id = "signupButton")
    private WebElement signupButton;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void gotoSignup() {
        this.signupButton.click();
    }

    public void gotoLogin() {
        this.loginButton.click();
    }
}
