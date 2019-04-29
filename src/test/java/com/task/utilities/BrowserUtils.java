package com.task.utilities;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BrowserUtils {


    //  ====================================|  OPERATION SYSTEM  |====================================
    public static final boolean ISWINDOWS;
    public static final boolean ISMAC;

    static {
        ISWINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");
        ISMAC = System.getProperty("os.name").toLowerCase().contains("mac");
    }



    //  ====================================|  SCREENSHOT  |====================================
    /**
     * Generates the screenshot in .png format and returns String path.
     *
     * @param name - Test name in String
     * @return     - Full path of the screenshot with .gitignore name in String
     *
     */
    public static String getScreenshot(String name){
        //name of the screenshot with date information
        String time = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();

        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String target = System.getProperty("user.dir")+"test-output/Screenshots"+name+"_"+time+".png";

        File finalDestination = new File(target);

        try {
            FileUtils.copyFile(source,finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;

    }



    //  ====================================|  SWITCH WINDOW  |====================================
    /**
     * Window handling - Switching to the window  with given tittle
     * Returns back if target windows is not found --> String origin
     *
     * @param targetWindow
     */
    public static void switchToWindow(String targetWindow){
        //current windows
        String origin = Driver.getDriver().getWindowHandle();
        for(String handle : Driver.getDriver().getWindowHandles()){
            Driver.getDriver().switchTo().window(handle);
            if(Driver.getDriver().getTitle().contains(targetWindow)){ return; }
        }

        //turn back to origin if not founded
        Driver.getDriver().switchTo().window(origin);
    }



    //  ====================================|  HOVER  |====================================
    /**
     * Hover over the WebElement
     * @param element  - Webelement which will be hovered
     */
    public static void hover(WebElement element){
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(element).build().perform();
    }



    //  ====================================|  WAIT  |====================================
    /**
     * Wait method
     * @param seconds duration in seconds to wait
     */
    public static void wait (int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        }
    }



    //  ====================================|  WAIT FOR VISIBILITY  |====================================
    /**
     * Explicit wait
     * @param element,duration WebElement and duration in seconds
     * @return -> wait.until
     */
    public static WebElement waitForVisibility (WebElement element, int duration){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),duration);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }



    //  ====================================|  WAIT TO CLICK  |====================================
    /**
     * Explicit wait
     * @param element,duration WebElement and duration in seconds
     * @return -> wait.until
     */
    public static WebElement waitToClick (WebElement element, int duration){
        WebDriverWait wait =new WebDriverWait(Driver.getDriver(),duration);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }



    //  ====================================|  WAIT PAGE TO LOAD  |====================================
    /**
     * Wait page to load
     * @param duration duration in seconds
     */
    public static void waitPageToLoad (int duration){
        Driver.getDriver().manage().timeouts().pageLoadTimeout(duration, TimeUnit.SECONDS);
    }



    //  ====================================|  FLUENT WAIT  |====================================
    /**
     * Fluent wait -->
     * @param webElement WebElement to be checked
     * @param duration duration to wait in seconds
     * @return --> element
     */
    public static WebElement fluentWait (WebElement webElement, int duration){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(duration))
                .pollingEvery(Duration.ofSeconds(duration/10))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webElement;
            }
        });
        return element;
    }



    //  ====================================|  WAIT FOR ELEMENTS TEXT MATCH  |====================================
    /**
     * Explicit wait until element's text is verified,
     *
     * @param element WebElement owner of the text
     * @param expectedText text in String
     * @param duration duration time in seconds
     * @return boolean
     */
    public static boolean waitForElementsTextMatch (WebElement element, String expectedText, int duration){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), duration);
        return wait.until(ExpectedConditions.textToBePresentInElement(element,expectedText));
    }



    //  ====================================|  OTHER BROWSER METHODS  |====================================


    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }























}
