package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class zLearn {
    static WebDriver driver;
    static int min = 2;
    static int max = 10;
    public static void main(String[] args)throws Exception {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.random.org/");

        System.out.println(driver.getCurrentUrl());
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe")));

        driver.findElement(By.cssSelector("[name = 'true-random-integer-generator-min']")).clear();
        driver.findElement(By.cssSelector("[name='true-random-integer-generator-min']")).sendKeys(String.valueOf(min));
        driver.findElement(By.cssSelector("[name='true-random-integer-generator-max']")).clear();
        driver.findElement(By.cssSelector("[name='true-random-integer-generator-max']")).sendKeys(String.valueOf(max));
        driver.findElement(By.cssSelector("[value='Generate']")).click();



        String resultWeb = driver.findElement(By.cssSelector("span#true-random-integer-generator-result")).getText();

        String myText = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).until(driver -> {
            String text = driver.findElement(By.cssSelector("span#true-random-integer-generator-result")).getText();

            return text.isEmpty() ? null : text;
        });
        //Возвращает пустое значение

        driver.switchTo().defaultContent();
        driver.quit();
        System.out.println(resultWeb);
        System.out.println(myText);



    }
}
