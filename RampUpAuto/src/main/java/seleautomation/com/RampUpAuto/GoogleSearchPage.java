package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
    private static WebElement element= null;
    public static WebElement TextBox_Search(WebDriver driver){
        element = driver.findElement(By.name("q"));
        return element;

    }

    public static WebElement button_search(WebDriver driver){
        element = driver.findElement(By.name("btnk"));
        return element;

    }
}
