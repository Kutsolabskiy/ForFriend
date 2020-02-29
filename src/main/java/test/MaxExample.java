package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxExample {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            driver.get("https://www.random.org/integers/");
            System.out.println(driver.getCurrentUrl());
            test1(33, 99, 200, 5);
        } finally {
            driver.quit();

        }
    }

    private static void test1(int countOfNumbers, int min, int max, int columnCount) {
        submitForm(countOfNumbers, min, max, columnCount);

        List<List<Integer>> result = getResults();

        checkData(countOfNumbers, min, max, columnCount, result);
    }

    private static void submitForm(int countOfNumbers, int min, int max, int columnCount) {
        driver.findElement(By.cssSelector("[name='num']")).clear();
        driver.findElement(By.cssSelector("[name='num']")).sendKeys(String.valueOf(countOfNumbers));
        driver.findElement(By.cssSelector("[name='min']")).clear();
        driver.findElement(By.cssSelector("[name='min']")).sendKeys(String.valueOf(min));
        driver.findElement(By.cssSelector("[name='max']")).clear();
        driver.findElement(By.cssSelector("[name='max']")).sendKeys(String.valueOf(max));
        driver.findElement(By.cssSelector("[name='col']")).clear();
        driver.findElement(By.cssSelector("[name='col']")).sendKeys(String.valueOf(columnCount));
        driver.findElement(By.cssSelector("[value='Get Numbers']")).click();
    }

    private static List<List<Integer>> getResults() {
        String text = driver.findElement(By.cssSelector(".data")).getText();
        return Stream.of(text.split("\n"))
                .map(row -> Stream.of(row.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static void checkData(int countOfNumbers, int min, int max, int columnCount, List<List<Integer>> actualData) {
        List<Integer> allNumbers = actualData.stream().flatMap(rowData -> rowData.stream()).collect(Collectors.toList());
        System.out.println("All numbers in range: " + allNumbers.stream().allMatch(number -> number <= max && number >= min));

        // check count of returned numbers
        System.out.println("Numbers count correct: " + (allNumbers.size() == countOfNumbers));

        // check columns size
        int remainder = countOfNumbers % columnCount;
        if (remainder == 0) {
            System.out.println("Col count correct: " + actualData.stream().allMatch(rowData -> rowData.size() == columnCount));
        } else {
            boolean isCorrectFullRows = actualData.subList(0, actualData.size() - 1).stream().allMatch(rowData -> rowData.size() == columnCount);
            boolean isCorrectLastRow = actualData.get(actualData.size() - 1).size() == remainder;
            System.out.println("Col count correct: " + (isCorrectFullRows && isCorrectLastRow));
        }
    }
}
