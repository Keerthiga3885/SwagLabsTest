package Pages;

import Utils.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AboutPage extends Base {

    WebDriver driver;

    @FindBy(xpath = "//button[text()='Open Menu']")
    WebElement btnMenu;

    @FindBy(id = "about_sidebar_link")
    WebElement btnAbout;

    @FindBy(xpath = "//span[text()='Products']")
    WebElement hdrProductPage;


    public AboutPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void aboutNavigationTest() {

        Properties properties = null;
        try (FileInputStream file = new FileInputStream("src/main/resources/Configuration.properties")) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println(" Unable to load properties file ");
        }

        //Navigating to about page
        waitToClick(driver, 10, btnMenu);
        btnMenu.click();
        btnAbout.click();

        //Validate about page url
        //waitUntilPageLoad(driver,10,properties.getProperty("AboutUrl"));
        String aboutUrl = driver.getCurrentUrl();
        assert properties != null;

        if (!aboutUrl.equalsIgnoreCase(properties.getProperty("AboutUrl"))) {
            throw new AssertionError("About button wrongly navigates to " + aboutUrl);
        }

        //Validate Products page header
        driver.navigate().back();
        waitUntilElementVisible(driver, 10, hdrProductPage);

    }

}
