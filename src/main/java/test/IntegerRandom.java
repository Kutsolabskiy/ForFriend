package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerRandom {
    static WebDriver driver;
    static int num = 24;
    static int min = 99;
    static int max = 200;
    static int col = 3;

    public static void main(String[] args) {
        submitForm();
    }

    private static void submitForm() {
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
        getResult();
    }

    private static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private static void getResult() {
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
    }

    private static void checkMinMax(List<List<Integer>> list) {
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

    private static <T> void checkNum2(List<List<T>> list) {
        int count = 0;
        for (List<T> strings : list) {
            count += strings.size();
        }
        if (count == num) {
            System.out.println("Count test is correct");
            checkColumn(list);
        } else {
            System.out.println("Count test is false");
        }
    }

    private static <T> void checkNum(List<List<T>> list) {
        List<T> numList = list.stream().flatMap(Collection::stream).collect(Collectors.toList());
        if(numList.size() == num){
            System.out.println("Num test is true");
            checkColumn(list);
        }else{
            System.out.println("Num test is false");
        }

    }

    private static <T> void checkColumn(List<List<T>> list) {
        for (int row = 0; row < list.size() - 1; row++) {
            if (!(list.get(row).size() == col)) {
                System.out.println("Column is false");
                return;
            }
        }
        System.out.println("Column is " + (list.get(list.size()-1).size() <= col));
    }

    private static <T> void checkColumnStream(List<List<T>> list){
    //Здесь было то самое чувство когда Нихуя не вышло
    }

    private static void testCheckColumn() {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(4, 5, 6, 4, 5),
                Arrays.asList(4, 5, 6, 4),
                Arrays.asList(4, 5, 6, 4, 4),
                Arrays.asList(4, 5, 6, 4, 5)
        );
        checkNum(input);
    }
}

