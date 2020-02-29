package test;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotReadyStringGenerator {
    static WebDriver driver;
    static int countStr = 10; //Max 10000
    static int longStr = 10;  //Max 20


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.random.org/strings/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.getCurrentUrl());
        uppercaseLetters();
        startTest(countStr, longStr);


    }

    public static void uppercaseLetters(){
        driver.findElement(By.cssSelector("[name=\"digits\"]")).click();
        driver.findElement(By.cssSelector("[name=\"upperalpha\"]")).click();
    }

    public static void startTest(int countStr, int longStr) {
        driver.findElement(By.cssSelector("[name=\"num\"]")).clear();
        driver.findElement(By.cssSelector("[name=\"num\"]")).sendKeys(String.valueOf(countStr));
        driver.findElement(By.cssSelector("[name=\"len\"]")).clear();
        driver.findElement(By.cssSelector("[name=\"len\"]")).sendKeys(String.valueOf(longStr));

        driver.findElement(By.cssSelector("[value=\"Get Strings\"]")).click();

        String locResult = driver.findElement(By.cssSelector(".data")).getText();
        driver.quit();

        List<String> resultList = Stream.of(locResult.split("\n"))
                .collect(Collectors.toList());
     //   List<List<String>> doubleList = Stream.of(locResult.split(""))



 //       resultList.stream().forEach(System.out::println);

        checkData(resultList);
    }
    public static void checkData(List<String> list){
        System.out.println(" " + (list.stream().allMatch(s -> s.length() == longStr)));
        System.out.println(" " + (list.size() == countStr));

    }

    public static void checkList(){
        char[] numList = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        char[] lowList = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    }

    public static void listList(List<String> list){

    }

}

