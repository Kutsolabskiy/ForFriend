package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerRandom extends WebdriverSetup {
    static int num = 25;
    static int min = 99;
    static int max = 200;
    static int col = 5;

    static List<List<Integer>> listInt = new ArrayList<>();

    @BeforeTest
    void submitForm() {
        getdriver().get("https://www.random.org/integers/");
        System.out.println(getdriver().getCurrentUrl());

        getdriver().findElement(By.cssSelector("[name='num']")).clear();
        getdriver().findElement(By.cssSelector("[name='num']")).sendKeys(String.valueOf(num));
        getdriver().findElement(By.cssSelector("[name='min']")).clear();
        getdriver().findElement(By.cssSelector("[name='min']")).sendKeys(String.valueOf(min));
        getdriver().findElement(By.cssSelector("[name='max']")).clear();
        getdriver().findElement(By.cssSelector("[name='max']")).sendKeys(String.valueOf(max));
        getdriver().findElement(By.cssSelector("[name='col']")).clear();
        getdriver().findElement(By.cssSelector("[name='col']")).sendKeys(String.valueOf(col));
        getdriver().findElement(By.cssSelector("[value='Get Numbers']")).click();

        String colResult = getdriver().findElement(By.cssSelector(".data")).getText();
        getResult(colResult);
    }

    void getResult(String colResult ) {

        for (String rowAsString : colResult.split("\n")) {
            List<Integer> rowNumbers = new ArrayList<>();
            for (String numberCell : rowAsString.split(" ")) {
                rowNumbers.add(Integer.parseInt(numberCell));
            }
            listInt.add(rowNumbers);
        }
    }

    @Test(priority = 1)
    void checkMinMax() {
        for (List<Integer> integers : listInt) {
            for (Integer integer : integers) {
                Assert.assertTrue((integer <= max || integer >= min));

            }
        }
    }

    @Test(priority = 2)
    void checkNum() {
        List<Integer> numList = listInt.stream().flatMap(Collection::stream).collect(Collectors.toList());
        Assert.assertTrue(numList.size() == num);

    }

    @Test(dependsOnMethods = "checkNum")
    void checkColumn() {
        for (int row = 0; row < listInt.size() - 1; row++) {
            if (!(listInt.get(row).size() == col)) {
                Assert.fail();
            }
        }
        Assert.assertTrue(listInt.get(listInt.size()-1).size() <= col);
    }
    /*static List<List<Integer>> listInt2 = Arrays.asList(
            Arrays.asList(1, 2, 3, 4, 5),
            Arrays.asList(4, 5, 6, 4, 5),
            Arrays.asList(4, 5, 6, 4, 4),
            Arrays.asList(4, 5, 6, 4, 4),
            Arrays.asList(4, 5, 6, 4, 5)
    );*/


    }


