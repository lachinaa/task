package com.task.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){
    }


    private static WebDriver driver;

    // driver getter
    public static WebDriver getDriver() {
        if(driver==null){
            // browser Driver setup
            switch(ConfigurationReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                // I can add more browsers from this line
            }
        }

        //impilicity Wait setup
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(12, TimeUnit.SECONDS);

        return driver;
    }

    //quit Driver
//    public static  void closeDriver(){
//        if (driver != null){
//            driver.quit();
//            driver = null;
//        }
    // }
}
