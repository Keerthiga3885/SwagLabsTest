package Pages;

import Utils.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginPage extends Base {

    WebDriver driver;

    @FindBy(id = "user-name")
    WebElement txtUserName;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "login-button")
    WebElement btnSignIn;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void login() {

        Properties properties = null;
        try (FileInputStream file = new FileInputStream("src/main/resources/Configuration.properties")) {

            properties = new Properties();
            properties.load(file);

        } catch (IOException e) {
            System.out.println(" Failed to load properties file ");
        }

        // Login to SwagLabs
        assert properties != null;
        driver.get(properties.getProperty("Url"));

        waitToClick(driver, 10, txtUserName);
        txtUserName.sendKeys(properties.getProperty("Username"));
        txtPassword.sendKeys(properties.getProperty("Password"));

        waitToClick(driver, 5, btnSignIn);
        btnSignIn.click();

    }

}
