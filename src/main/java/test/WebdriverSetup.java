package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class WebdriverSetup{
    public static WebDriver driver = null;
    public WebdriverSetup()
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

    @AfterTest
    public static void closeDriver(){
        driver.quit();
    }
}