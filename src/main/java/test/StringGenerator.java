package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringGenerator extends WebdriverSetup {
    static int countStr = 5; //Max 10000
    static int longStr = 10;  //Max 20
    static boolean numericDigits = false;
    static boolean uppercaseLetters = true;
    static boolean lowercaseLetters = false;

    static List<String> listCheckBox = new ArrayList<>();
    static List<String> resultList = new ArrayList<>();


    @BeforeTest
    void checkBox(){
        listCheckBox.add("[");
        getdriver().get("https://www.random.org/strings/");
        if(!numericDigits) {
            getdriver().findElement(By.cssSelector("[name=\"digits\"]")).click();
            }else{
                listCheckBox.add("0-9");
        }
        if (uppercaseLetters){
            getdriver().findElement(By.cssSelector("[name=\"upperalpha\"]")).click();
            listCheckBox.add("A-Z");
        }
        if (lowercaseLetters){
            getdriver().findElement(By.cssSelector("[name=\"loweralpha\"]")).click();
            listCheckBox.add("a-z");
        }
        listCheckBox.add("]+");

        submitForm();
    }

    void submitForm() {
        System.out.println(getdriver().getCurrentUrl());
        getdriver().findElement(By.cssSelector("[name=\"num\"]")).clear();
        getdriver().findElement(By.cssSelector("[name=\"num\"]")).sendKeys(String.valueOf(countStr));
        getdriver().findElement(By.cssSelector("[name=\"len\"]")).clear();
        getdriver().findElement(By.cssSelector("[name=\"len\"]")).sendKeys(String.valueOf(longStr));

        getdriver().findElement(By.cssSelector("[value=\"Get Strings\"]")).click();

        getResultForm();
    }

    void getResultForm(){
        String locResult = getdriver().findElement(By.cssSelector(".data")).getText();

        resultList = Stream.of(locResult.split("\n"))
                .collect(Collectors.toList());


    }

    @Test(priority = 1)
    void checkLengthsRows(){
        Assert.assertTrue(resultList.stream().allMatch(s -> s.length() == longStr));
    }

    @Test(priority = 2)
    void checkCountsRows(){
        Assert.assertTrue(resultList.size() == countStr);
    }

    @Test(priority = 3)
    void checkChars(){
        String regularExpressions = checkChar(resultList);
        Assert.assertTrue(resultList.stream().allMatch(s -> s.matches(regularExpressions)));
    }


    public static String checkChar(List<String> list){
        String share = "";
        return String.join(share, listCheckBox);
    }
}