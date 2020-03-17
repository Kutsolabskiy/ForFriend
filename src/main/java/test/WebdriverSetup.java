package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class Webdriverdefinition {
    public static WebDriver driver = null;
    public Webdriverdefinition()
    {
        if(driver == null) {

            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
            driver = new ChromeDriver();
        }
    }

    public static WebDriver getdriver()
    {
        if (driver == null){
            return driver;
        }else{
            return driver;
        }
    }

    @AfterMethod
    public static void closeDriver(){
        driver.quit();
    }
}