package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringGenerator {
    static WebDriver driver;
    static int countStr = 5; //Max 10000
    static int longStr = 10;  //Max 20
    static boolean numericDigits = false;
    static boolean uppercaseLetters = true;
    static boolean lowercaseLetters = false;

    static List<String> listCheckBox = new ArrayList<>();

    public static void main(String[] args) {
        createDriver();
    }

    public static void createDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.random.org/strings/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        checkBox(numericDigits, uppercaseLetters, lowercaseLetters);
    }

    public static void checkBox(boolean checkBoxNum, boolean checkBoxUpcase, boolean checkBoxLowcase){
        listCheckBox.add("[");
        if(!numericDigits) {
            driver.findElement(By.cssSelector("[name=\"digits\"]")).click();
            }else{
                listCheckBox.add("0-9");
        }
        if (checkBoxUpcase){
            driver.findElement(By.cssSelector("[name=\"upperalpha\"]")).click();
            listCheckBox.add("A-Z");
        }
        if (checkBoxLowcase){
            driver.findElement(By.cssSelector("[name=\"loweralpha\"]")).click();
            listCheckBox.add("a-z");
        }
        listCheckBox.add("]+");

        submitForm(countStr, longStr);
    }

    public static void submitForm(int countStr, int longStr) {
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("[name=\"num\"]")).clear();
        driver.findElement(By.cssSelector("[name=\"num\"]")).sendKeys(String.valueOf(countStr));
        driver.findElement(By.cssSelector("[name=\"len\"]")).clear();
        driver.findElement(By.cssSelector("[name=\"len\"]")).sendKeys(String.valueOf(longStr));

        driver.findElement(By.cssSelector("[value=\"Get Strings\"]")).click();

        getResultForm();
    }

    public static void getResultForm(){
        String locResult = driver.findElement(By.cssSelector(".data")).getText();
        driver.quit();

        List<String> resultList = Stream.of(locResult.split("\n"))
                .collect(Collectors.toList());

        checkData(resultList);
    }

    public static void checkData(List<String> list){
        String regularExpressions = checkChar(list);

        System.out.println("Lengths rows is: " + (list.stream().allMatch(s -> s.length() == longStr)));
        System.out.println("Counts rows is: " + (list.size() == countStr));
        System.out.println("All value char is: " + (list.stream().allMatch(s -> s.matches(regularExpressions))));
    }

    public static String checkChar(List<String> list){
        String share = "";
        return String.join(share, listCheckBox);
    }
}