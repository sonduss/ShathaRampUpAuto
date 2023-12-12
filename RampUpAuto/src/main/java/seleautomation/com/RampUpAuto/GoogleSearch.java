package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

   public class TestTest  {
WebDriver driver;
@BeforeMethod
   public void setup()  {


    System.setProperty("webdriver.chrome.driver", "C:\\Users\\srabaya\\IdeaProjects\\Selenium\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://www.google.com");
    driver.manage().window().maximize();

}
@Test
   public void GoogleSearchTC() throws InterruptedException {

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.findElement(By.name("q")).sendKeys("Selenium Tutorials");
    driver.findElement(By.xpath("//input[@name='btnK']")).click();
    Thread.sleep(10000);
    String ExpectedResult = "Selenium Tutorials - بحث Google\u200F";
    String ActualResult = driver.getTitle();
    System.out.println(ActualResult);
    Assert.assertEquals(ActualResult, ExpectedResult);
    System.out.println("Passed TC");
}
@AfterMethod
    public void Close() {
        driver.quit();
    }


}




