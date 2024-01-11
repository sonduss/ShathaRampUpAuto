package RampUpAuto.src.test.java.seleautomation.com.RampUpAuto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


import java.lang.reflect.Method;


public class GitHubSignInTestNew {
    WebDriver driver;
    GitHubSignInPage gitHubSignInPage;


    @BeforeClass
    @Parameters("browser")
    public void create(@Optional("edge")String browser) {


        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("Firefox is lunched");
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\srabaya\\IdeaProjects\\GithubSigninProject\\geckodriver.exe");
            driver = new FirefoxDriver();


        } else if (browser.equalsIgnoreCase("chrome")) {

            System.out.println("Chrome is lunched");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\srabaya\\IdeaProjects\\GithubSigninProject\\chromedriver.exe");
            driver = new ChromeDriver();


        } else if (browser.equalsIgnoreCase("edge")) {

            System.out.println("Edge is lunched");
            System.setProperty("webdriver.edge.driver", "C:\\Users\\srabaya\\IdeaProjects\\GithubSigninProject\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        gitHubSignInPage = new GitHubSignInPage(driver);
    }

    @DataProvider(name = "GitHubSignInn")

    public Object[][] DBgithubSignin(Method m) {

        return new Object[][] {

                { "asaltest1646449@gmail.com", "password123456789s*", false },

                { "asaltest19@gmail.com", "password123456789sgg", false },

                { "", "", false },

                { "shatha.rabaya71@gmail.com", "Sh.Sh123456", true }

        };

    }

    @BeforeMethod
    public void LoginBeforeTest() {

        gitHubSignInPage.LoginPage();
    }

    @Test(dataProvider = "GitHubSignInn")

    public void githubSignIn(String username, String password, boolean validTC) {

        gitHubSignInPage.FillUserName(username);
        gitHubSignInPage.FillPassword(password);
        gitHubSignInPage.clickButton();

        if (validTC) {

            gitHubSignInPage.isSignInSuccessful();

        } else {

            gitHubSignInPage.isErrorMessageDisplayed();

        }
    }

    @AfterClass

    public void CloseBrowser() {

        driver.quit();

    }

}


