package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class PasswordSimple extends WebdriverSetup {
    static int num = 100; //maximum 100
    static int len = 24; // must be 6\24

    static String[] listClick;

    @BeforeTest
    void submitForm() {

        getdriver().get("https://www.random.org/passwords/");
        System.out.println(getdriver().getCurrentUrl());
        getdriver().findElement(By.cssSelector("[name='num']")).clear();
        getdriver().findElement(By.cssSelector("[name='num']")).sendKeys(String.valueOf(num));
        getdriver().findElement(By.cssSelector("[name='len']")).clear();
        getdriver().findElement(By.cssSelector("[name='len']")).sendKeys(String.valueOf(len));
        getdriver().findElement(By.cssSelector("[value=\"Get Passwords\"]")).click();

        String getClick = getdriver().findElement(By.cssSelector(".data")).getText();
        listClick = getClick.split("\n");
    }

    @Test(priority = 1)
    void checkLength() {
        Assert.assertTrue(Stream.of(listClick).allMatch(s -> s.length() == len));
    }

    @Test(priority = 2)
    void checkCountPassword() {
        Assert.assertEquals(num, listClick.length);
    }

}

