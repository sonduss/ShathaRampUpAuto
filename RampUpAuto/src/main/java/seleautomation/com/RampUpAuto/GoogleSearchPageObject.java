package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class GoogleSearchPageObjects {
    static WebDriver driver;
    By TextBox_search = By.name("q");
    By button_search = By.name("btnk");

    public GoogleSearchPageObjects (WebDriver driver){
        this.driver = driver;
    }

    public void setTestInSearchBox(String text){
        driver.findElement(TextBox_search).sendKeys(text);

    }

    public void ClickSearchButton(){
        driver.findElement(button_search).sendKeys(Keys.RETURN);


    }

}
