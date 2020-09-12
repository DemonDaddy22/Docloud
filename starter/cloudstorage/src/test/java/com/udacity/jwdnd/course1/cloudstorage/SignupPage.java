package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstname;

    @FindBy(id = "inputLastName")
    private WebElement lastname;

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submitButton")
    private WebElement submit;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void registerUser(WebDriver driver, String firstname, String lastname, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.firstname)).sendKeys(firstname);

        wait.until(ExpectedConditions.elementToBeClickable(this.lastname)).sendKeys(lastname);

        wait.until(ExpectedConditions.elementToBeClickable(this.username)).sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(this.password)).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(this.submit)).click();
    }
}
