package com.task.step_def;

import com.task.pages.LoginPage;
import com.task.utilities.BrowserUtils;
import com.task.utilities.ConfigurationReader;
import com.task.utilities.Driver;
import com.task.utilities.ExcelUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import java.util.List;
import java.util.Map;

public class LoginPageStepDefinition {

    LoginPage loginPage = new LoginPage();

    @Given("user goes to the main page {string}")
    public void user_goes_to_the_main_page(String string) {

        string = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(string);
    }

    @When("user enters invalid credentials,user should be able to see error message")
    public void user_enters_invalid_credentials() {


        loginPage.username_tab.sendKeys("$.");
        loginPage.password_tab.sendKeys("xlko2M");
        loginPage.signIn.click();
        Assert.assertEquals(loginPage.signInPopupErrorMessage.getText(), "Sign in failed!");
    }


//**********************************************DATA-DRIVEN TESTING*****************************************************

    @When("user logs in using {string} and {string}")
    public void user_logs_in_using_and(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.username_tab.sendKeys(username);
        loginPage.password_tab.sendKeys(password);
        loginPage.signIn.click();

    }

    @Then("user should see error message")
    public void user_should_see_error_message() {
        Assert.assertEquals(loginPage.signInPopupErrorMessage.getText(), "Sign in failed!");

    }


    //******************************* Here data will be read from excel .gitignore********************************************
    @Given("user should enter invalid {string} and {string}, and should be able to see the error message")
    public void user_should_enter_invalid_and_and_should_be_able_to_see_the_error_message(String string, String string2) throws InterruptedException {


        String file = "./src/test/resources/test_data/mock_data.xlsx";
        String sheet = "Sheet 1 - MOCK_DATA-15";
        ExcelUtil pl = new ExcelUtil(file, sheet);


        List<Map<String, String>> usernameAndPasswordList = pl.getDataList();

        for (Map<String, String> credentials : usernameAndPasswordList) {

            Driver.getDriver().navigate().refresh();
            String usernameCredentials = credentials.get("Username");
            loginPage.username_tab.sendKeys(usernameCredentials, Keys.ENTER);

            String passwordCredentials = credentials.get("Password");
            loginPage.password_tab.sendKeys(passwordCredentials, Keys.ENTER);
            BrowserUtils.wait(2);
            loginPage.signIn.click();
            Assert.assertEquals(loginPage.signInPopupErrorMessage.getText(), "Sign in failed!");
        }

    }
}
