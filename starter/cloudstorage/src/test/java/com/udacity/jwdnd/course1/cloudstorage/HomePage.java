package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage {

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "newNoteButton")
    private WebElement newNoteButton;

    @FindBy(id = "note-title")
    private WebElement inputNotetitle;

    @FindBy(id = "note-description")
    private WebElement inputNoteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(id = "noteTitle")
    private WebElement noteTitle;

    @FindBy(id = "noteDescription")
    private WebElement noteDescription;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id = "saveNoteButton")
    private WebElement saveNoteButton;

    @FindBy(id = "newCredentialButton")
    private WebElement newCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement inputCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id = "credential-username")
    private WebElement inputCredentialPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmit;

    @FindBy(id = "credentialUrl")
    private WebElement credentialUrl;

    @FindBy(id = "credentialUsername")
    private WebElement credentialUsername;

    @FindBy(id = "credentialPassword")
    private WebElement credentialPassword;

    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;

    @FindBy(id = "saveCredentialButton")
    private WebElement saveCredentialButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public boolean checkElementPresent(WebDriver driver, String id) {
        try {
            driver.findElement(By.id(id));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void createNewNote(WebDriver driver, String noteTitle, String noteDescription) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.notesTab)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.newNoteButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.inputNotetitle)).sendKeys(noteTitle);

        wait.until(ExpectedConditions.elementToBeClickable(this.inputNoteDescription)).sendKeys(noteDescription);

        wait.until(ExpectedConditions.elementToBeClickable(this.noteSubmit)).click();
    }

    public List<String> getNote(WebDriver driver) {
        List<String> createdNotes = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.notesTab)).click();

        createdNotes.add(wait.until(ExpectedConditions.elementToBeClickable(this.noteTitle)).getText());

        createdNotes.add(wait.until(ExpectedConditions.elementToBeClickable(this.noteDescription)).getText());

        return createdNotes;
    }

    public void editNote(WebDriver driver, String noteTitle, String noteDescription) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.notesTab)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.editNoteButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.inputNotetitle)).sendKeys(noteTitle);

        wait.until(ExpectedConditions.elementToBeClickable(this.inputNoteDescription)).sendKeys(noteDescription);

        wait.until(ExpectedConditions.elementToBeClickable(this.noteSubmit)).click();
    }

    public void deleteNote(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.notesTab)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.deleteNoteButton)).click();
    }

    public boolean checkNotePresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.notesTab)).click();

        return checkElementPresent(driver, "noteTitle") && checkElementPresent(driver, "noteDescription");
    }

    public void createNewCredential(WebDriver driver, String credentialUrl, String credentialUsername, String credentialPassword) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.credentialsTab)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.newCredentialButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.inputCredentialUrl)).sendKeys(credentialUrl);

        wait.until(ExpectedConditions.elementToBeClickable(this.inputCredentialUsername)).sendKeys(credentialUsername);

        wait.until(ExpectedConditions.elementToBeClickable(this.inputCredentialPassword)).sendKeys(credentialPassword);

        wait.until(ExpectedConditions.elementToBeClickable(this.credentialSubmit)).click();
    }

    public List<String> getCredential(WebDriver driver) {
        List<String> createdCredentials = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.credentialsTab)).click();

        createdCredentials.add(wait.until(ExpectedConditions.elementToBeClickable(this.credentialUrl)).getText());

        createdCredentials.add(wait.until(ExpectedConditions.elementToBeClickable(this.credentialUsername)).getText());

        createdCredentials.add(wait.until(ExpectedConditions.elementToBeClickable(this.credentialPassword)).getText());

        return createdCredentials;
    }

    public void editCredential(WebDriver driver, String credentialUrl, String credentialUsername, String credentialPassword) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.credentialsTab)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.editCredentialButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.inputCredentialUrl)).sendKeys(credentialUrl);

        wait.until(ExpectedConditions.elementToBeClickable(this.inputCredentialUsername)).sendKeys(credentialUsername);

        wait.until(ExpectedConditions.elementToBeClickable(this.inputCredentialPassword)).sendKeys(credentialPassword);

        wait.until(ExpectedConditions.elementToBeClickable(this.credentialSubmit)).click();
    }

    public void deleteCredential(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.credentialsTab)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.deleteCredentialButton)).click();
    }

    public boolean checkCredentialPresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.elementToBeClickable(this.credentialsTab)).click();

        return  checkElementPresent(driver, "credentialUrl") && checkElementPresent(driver, "credentialUsername") &&  checkElementPresent(driver, "credentialPassword");
    }
}