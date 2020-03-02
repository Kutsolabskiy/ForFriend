package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerRandom {
    static WebDriver driver;
    static int num = 33;
    static int min = 99;
    static int max = 200;
    static int col = 5;

    public static void main(String[] args) {
        testCheckColumn();
    }

    private static void testCheckColumn() {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(4, 5, 6),
                Arrays.asList(5, 6, 7)
        );
        checkColumn(input);
    }

    private static void test() {
        setupDriver();
        driver.get("https://www.random.org/integers/");
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("[name='num']")).clear();
        driver.findElement(By.cssSelector("[name='num']")).sendKeys(String.valueOf(num));
        driver.findElement(By.cssSelector("[name='min']")).clear();
        driver.findElement(By.cssSelector("[name='min']")).sendKeys(String.valueOf(min));
        driver.findElement(By.cssSelector("[name='max']")).clear();
        driver.findElement(By.cssSelector("[name='max']")).sendKeys(String.valueOf(max));
        driver.findElement(By.cssSelector("[name='col']")).clear();
        driver.findElement(By.cssSelector("[name='col']")).sendKeys(String.valueOf(col));
        driver.findElement(By.cssSelector("[value='Get Numbers']")).click();

        String colResult = driver.findElement(By.cssSelector(".data")).getText();
        driver.quit();

        List<List<Integer>> listInt = new ArrayList<>();
        for (String rowAsString : colResult.split("\n")) {
            List<Integer> rowNumbers = new ArrayList<>();
            for (String numberCell : rowAsString.split(" ")) {
                rowNumbers.add(Integer.parseInt(numberCell));
            }
            listInt.add(rowNumbers);
        }

        checkNum(listInt);
        checkMinMax(listInt);
        checkColumn(listInt);
    }

    private static void submitForm() {
        // todo
    }

    private static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static <T> void checkNum(List<List<T>> list) {
        int count = 0;
        for (List<T> strings : list) {
            count += strings.size();
        }
        if (count == num) {
            System.out.println("Count test is correct");
        } else {
            System.out.println("error404");
        }
    }

    public static void checkMinMax(List<List<Integer>> list) {
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                if (!(integer <= max || integer >= min)) {
                    System.out.println("сожги себе ебало гнида криворукая");
                    return;
                }
            }
        }
        System.out.println("Min/Max test is correct");
    }

    public static void checkColumn(List<List<Integer>> list) {
        int error = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == col) {
                continue;
            } else if (list.get(i).size() == num % col) {
                continue;
            } else {
                error++;
            }
        }
        if (error == 0) {
            System.out.println("Check Colum test is correct");
        } else {
            System.out.println("shit");
        }
    }
}

