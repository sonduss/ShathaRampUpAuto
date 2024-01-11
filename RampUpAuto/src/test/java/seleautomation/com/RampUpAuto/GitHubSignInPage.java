package seleautomation.com.RampUpAuto;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

public class GitHubSignInPage {

    static WebDriver driver;

    public By usernameField = By.id("login_field");
    public By passwordField = By.id("password");
    public By LoginButton = By.name("commit");

    public GitHubSignInPage(WebDriver driver) {

        this.driver = driver;

    }

    public void LoginPage() {
        driver.get("https://github.com/login");
    }

    public void FillUserName(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void FillPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickButton() {
        driver.findElement(LoginButton).click();
    }


    public boolean isSignInSuccessful() {
        String actualUrl = "https://github.com/";
        String expectedUrl = driver.getCurrentUrl();
        if (actualUrl.equalsIgnoreCase(expectedUrl)) {
            System.out.println("Test passed");
            return true;
        } else {
            System.out.println("Test failed");
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        WebElement errorMessage = driver.findElement(By.cssSelector(".flash-error"));
        return errorMessage.isDisplayed();

    }

}


