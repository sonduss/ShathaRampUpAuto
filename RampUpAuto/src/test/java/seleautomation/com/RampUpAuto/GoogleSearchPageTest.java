package Testing;

import Pages.GoogleSearchPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GoogleSearchPageTest {

    static WebDriver driver;

    public static void main(String[] args) {

    }

    @BeforeClass
    public  static void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\srabaya\\IdeaProjects\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");

    }

    @Test
    public static void googlesearchTest() {

        GoogleSearchPageObjects searchObj = new GoogleSearchPageObjects(driver);
        searchObj.setTestInSearchBox("Selenium Tutorials");
        searchObj.ClickSearchButton();
        String ExpectedResult = "Selenium Tutorials - بحث Google";
        String ActualResult = driver.getTitle();
        System.out.println(ActualResult);
        Assert.assertEquals(ActualResult, ExpectedResult);
        System.out.println("Passed TC");

    }

    @AfterClass
    public void Close() {
        driver.quit();
    }
}
