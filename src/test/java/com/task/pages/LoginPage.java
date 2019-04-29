package com.task.pages;


import com.task.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='okta-signin-username']")
    public WebElement username_tab;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement password_tab;

    @FindBy(xpath = "//input[@class='button button-primary']")
    public WebElement signIn;


    @FindBy(xpath = "//div[@id='qtip-2-content']")
    public WebElement popupMessage;

    @FindBy(xpath = "//div[@class='okta-form-infobox-error infobox infobox-error']")
    public WebElement signInPopupErrorMessage;

}
