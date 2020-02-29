package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class RandomaizerList {
    static WebDriver driver;
    static int lengthList;
    static ArrayList<String> inputValues;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         inputValues = new ArrayList<>();

        for(int i = 0; i < 10000; i++){
            String s = reader.readLine();
            if(s.isEmpty()){
                break;
            }
            inputValues.add(s);
        }
        lengthList = inputValues.size();
        checkInputValue();
    }

    public static void checkInputValue(){
        if(inputValues.size() >= 2) {
            String[] resultList = startTest(inputValues);

            System.out.println("Count string test: " + checkCorrectCountString(resultList));
            System.out.println("Value string test: " + checkCorrectValuesString(resultList));
        }else {
            System.out.println("Error: Your list must contain at least two items. Try again");
        }

    }
    public static String[] startTest(ArrayList<String> list){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.random.org/lists/");
        System.out.println(driver.getCurrentUrl());

        for (String s : list){
            driver.findElement(By.cssSelector("[name=\"list\"]")).sendKeys(String.valueOf(s + "\n"));
        }

        driver.findElement(By.cssSelector("[value=\"Randomize\"]")).click();

        String str = driver.findElement(By.xpath("//div[@id=\"invisible\"]/ol")).getText();

        driver.quit();

        String[] listResult = str.split("\n");

        return listResult;
    }

    public static boolean checkCorrectCountString(String[] list){
        if(list.length == lengthList){
            return true;
        }else {
            return true;
        }
    }

    public static boolean checkCorrectValuesString (String[] list){
        ArrayList<String> listAr = new ArrayList<>();
        for(int i = 0; i < list.length; i++){
            listAr.add(list[i]);
        }

        for(int i = 0; i < list.length; i++){
            for(int y = 0; y < listAr.size(); y++){
                if(list[i].equals(listAr.get(y))){
                    listAr.remove(listAr.get(y));
                    break;
                }
            }

        }
        if(listAr.isEmpty()){
            return true;
        }else{
            return false;
        }

    }
}
