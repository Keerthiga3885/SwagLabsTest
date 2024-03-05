package SwagLabsTest;

import Pages.AboutPage;
import Pages.LoginPage;
import Utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class SwagLabsTest {

    @Test
    public void loginTest() {

        WebDriver loginDriver = DriverUtils.initBrowser("chrome");
        LoginPage loginPage = new LoginPage(loginDriver);

        loginPage.login();
        DriverUtils.tearDown(loginDriver);

    }

    @Test
    public void aboutNavigationTest() {

        WebDriver aboutNavigationDriver = DriverUtils.initBrowser("chrome");

        LoginPage loginPage = new LoginPage(aboutNavigationDriver);
        loginPage.login();

        AboutPage aboutPage = new AboutPage(aboutNavigationDriver);
        aboutPage.aboutNavigationTest();

        DriverUtils.tearDown(aboutNavigationDriver);

    }

}
