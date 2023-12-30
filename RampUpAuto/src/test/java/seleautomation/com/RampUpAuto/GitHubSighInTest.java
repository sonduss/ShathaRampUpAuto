package seleautomation.com.RampUpAuto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

class GitHubSignInPage {

    WebDriver driver;

    GitHubSignInPage(WebDriver driver) {

        this.driver = driver;

    }

    public void signIn(String username, String password) {

        WebElement usernameInput = driver.findElement(By.id("login_field"));

        WebElement passwordInput = driver.findElement(By.id("password"));

        WebElement signInButton = driver.findElement(By.name("commit"));

        usernameInput.sendKeys(username);

        passwordInput.sendKeys(password);

        signInButton.click();

    }

    public boolean isSignInSuccessful() {

        WebElement userAvatar = driver.findElement(By.className("avatar"));

        return userAvatar.isDisplayed();

    }

    public boolean isErrorMessageDisplayed() {

        WebElement errorMessage = driver.findElement(By.cssSelector(".flash-error"));

        return errorMessage.isDisplayed();

    }


}

public class GitHubSignInTest {
    WebDriver driver;
    public GitHubSignInPage signInPage;

    FirefoxOptions firefoxOptions = new FirefoxOptions();
    ChromeOptions chromeOptions = new ChromeOptions();
    EdgeOptions edgeOptions = new EdgeOptions();


    @BeforeTest
    @Parameters("browser")
    public void create(String browser) throws Exception {


        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("Firefox is lunched");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();


        } else if (browser.equalsIgnoreCase("chrome")) {

            System.out.println("Chrome is lunched");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();


        } else if (browser.equalsIgnoreCase("Edge")) {

            System.out.println("Edge is lunched");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();


        } else {
            throw new Exception("Invalid browser");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://github.com/login");
        driver.manage().window().maximize();
    }


    @Test(priority = 0)

    public void signInWithValidCredentials() {

        signInPage.signIn("asaltest19@gmail.com", "password123456789s*");

        Assert.assertTrue(signInPage.isSignInSuccessful(), "Login failed. User not redirected to the expected page.");

    }

    @Test(dependsOnMethods = "signInWithValidCredentials", priority = 1)
    public void SignOutAfterSuccessLogin() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement userMenu = driver.findElement(By.className("avatar"));
        userMenu.click();
        WebElement signOutButton = driver.findElement(By.linkText("Sign out"));
        signOutButton.click();
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/main/div/div[3]/div/div[2]/form/input[3]")).click();

    }


    @Test(dependsOnMethods = "SignOutAfterSuccessLogin", priority = 2)

    public void signInWithInvalidUserName() {
        driver.navigate().to("https://github.com/login");

        signInPage.signIn("asaltest1646449@gmail.com", "password123456789s*");

        Assert.assertFalse(signInPage.isSignInSuccessful(), "Login succeeded with invalid credentials.");

        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), "Error message not displayed for invalid login.");

    }

    @Test(priority = 3)

    public void signInWithInvalidPassword() {
        driver.navigate().to("https://github.com/login");

        signInPage.signIn("asaltest19@gmail.com", "password123456789sgg");

        Assert.assertFalse(signInPage.isSignInSuccessful(), "Login succeeded with invalid credentials.");

        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), "Error message not displayed for invalid login.");
    }

    @Test(priority = 4)

    public void signInWithEmptyFileds() {
        driver.navigate().to("https://github.com/login");

        signInPage.signIn("", "");

        Assert.assertFalse(signInPage.isSignInSuccessful(), "Login succeeded with invalid credentials.");

        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), "Error message not displayed for invalid login.");
    }
    @AfterClass

    public void tearDown() {

        driver.quit();

    }

}


