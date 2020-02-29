package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PasswordSimple {
    static WebDriver driver;
    static int num = 100; //maximum 100
    static int len = 24; // must be 6\24
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");



        if (num >= 1 && num <= 100 && len >= 6 && len <= 24) {    //Проверка входящих значений на тест
            try {
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get("https://www.random.org/passwords/");
                System.out.println(driver.getCurrentUrl());
                driver.findElement(By.cssSelector("[name='num']")).clear();
                driver.findElement(By.cssSelector("[name='num']")).sendKeys(String.valueOf(num));
                driver.findElement(By.cssSelector("[name='len']")).clear();
                driver.findElement(By.cssSelector("[name='len']")).sendKeys(String.valueOf(len));
                driver.findElement(By.cssSelector("[value=\"Get Passwords\"]")).click();

                String getClick = driver.findElement(By.cssSelector(".data")).getText();

                driver.quit();

                String[] listClick = getClick.split("\n");

                checkLength(listClick);
                checkCountPassword(listClick);


            } finally {

            }
        }else {
            System.out.println("The entered values do not meet the requirements");
            System.out.println("Try again");
        }
    }

    public static void checkLength (String[] list){
        for(String s : list){
            int i = s.length();
            if(i == len){
                continue;
            }else {
                System.out.println("Error");
                break;
            }
        }
        System.out.println("Length test is correct");
    }

    public static void checkCountPassword (String[] list){
        if(list.length == num){
            System.out.println("Test count password is correct");
        }else{
            System.out.println("error");
        }
    }
}
