package com.Github;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import org.testng.Assert;


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


    @BeforeTest
    @Parameters("browser")
    public void create(@Optional("chrome")String browser) throws Exception {


        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("Firefox is lunched");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\srabaya\\IdeaProjects\\Selenium\\geckodriver.exe");
            driver = new FirefoxDriver();


        } else if (browser.equalsIgnoreCase("chrome")) {

            System.out.println("Chrome is lunched");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\srabaya\\IdeaProjects\\Selenium\\chromedriver.exe");
            driver = new ChromeDriver();


        } else if (browser.equalsIgnoreCase("Edge")) {

            System.out.println("Edge is lunched");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\srabaya\\IdeaProjects\\Selenium\\msedgedriver.exe");
            driver = new EdgeDriver();


        } else {
            throw new Exception("Invalid browser");
        }
        driver.get("https://github.com/login");
        driver.manage().window().maximize();
    }

    @DataProvider(name = "GitHubSignInn")

    public Object[][] DBgithubSignin() {

        return new Object[][] {

                { "asaltest19@gmail.com", "password123456789s*", true },

                { "asaltest1646449@gmail.com", "password123456789s*", false },

                { "asaltest19@gmail.com", "password123456789sgg", false },

                { "", "", false }

        };

    };


    @Test(dataProvider = "GitHubSignInn")

    public void githubSignIn(String username, String password, boolean valid) {

        signInPage.signIn(username, password);



        if (valid) {

            Assert.assertFalse(signInPage.isSignInSuccessful(), "Login succeeded with invalid credentials.");


        } else {

            Assert.assertTrue(signInPage.isErrorMessageDisplayed(), "Error message not displayed for invalid login.");

        }

    }
    @AfterClass

    public void tearDown() {

        driver.quit();

    }

}


