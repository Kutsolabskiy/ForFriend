package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IntegerRandom {
    static WebDriver driver;
    static int num = 33;
    static int min = 99;
    static int max = 200;
    static int col = 5;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

        String [] listS = colResult.split("\n");

        List<List<String>> list = new ArrayList<>();
        for(String s : listS){
            list.add(Arrays.asList(s.split(" ")));
        }

        List<List<Integer>> listInt = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            ArrayList<Integer> listIntsmall = new ArrayList<>();
            for(int j = 0; j < list.get(i).size(); j++){
                int x = Integer.parseInt(list.get(i).get(j));
                listIntsmall.add(x);
        }
            listInt.add(listIntsmall);
        }

        checkNum(list);
        checkMinMax(listInt);
        checkColum(listInt);

    }
    public static void checkNum(List<List<String>> list){
        int count = 0;
        for(int i = 0; i < list.size(); i++){
             for(int j = 0; j < list.get(i).size(); j++){
            count ++;
            }
        }
        if(count == num){
            System.out.println("Count test is correct");
        }else {
            System.out.println("error404");
        }
    }
    public static void checkMinMax(List<List<Integer>> list){
        int error = 0;
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).size(); j++){
                if(list.get(i).get(j) <= max || list.get(i).get(j) >= min){
                    continue;
                }else{
                    error = 1;
                    break;
                }
            }
        }
        if(error == 0){
            System.out.println("Min/Max test is correct");
        }else{
            System.out.println("сожги себе ебало гнида криворукая");
        }
    }
    public static void checkColum (List<List<Integer>> list) {
        int error = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).size() == col){
                continue;
            }else if(list.get(i).size() == num % col ){
                continue;
            }else{
                error++;
            }
        }
        if(error == 0){
            System.out.println("Check Colum test is correct");
        }else {
            System.out.println("shit");
        }
    }
}

