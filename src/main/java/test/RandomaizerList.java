package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomaizerList extends WebdriverSetup {

    static List<String> inputValuesList = new ArrayList<>();     //заполнить перед стартом, или сасать...

    static List<String> resultList = new ArrayList<>();



    @BeforeTest
    void submitFormList() {

        inputValuesList.add("q");
        inputValuesList.add("q");
        inputValuesList.add("q");
        inputValuesList.add("qe");
        inputValuesList.add("q");


        getdriver().get("https://www.random.org/lists/");
        System.out.println(getdriver().getCurrentUrl());

        for (String s : inputValuesList) {
            getdriver().findElement(By.cssSelector("[name=\"list\"]")).sendKeys(String.valueOf(s + "\n"));
        }

        getdriver().findElement(By.cssSelector("[value=\"Randomize\"]")).click();

        String str = getdriver().findElement(By.xpath("//div[@id=\"invisible\"]/ol")).getText();

        resultList = Stream.of(str.split("\n")).collect(Collectors.toList());
    }

    @Test(priority = 1)
    void checkCorrectCountString() {
        Assert.assertTrue(resultList.size() == inputValuesList.size());

    }

    @Test(priority = 2)
    void checkCorrectValuesString() {

        Assert.assertTrue(inputValuesList.containsAll(resultList));

    }
}

