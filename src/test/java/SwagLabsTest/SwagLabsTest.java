package SwagLabsTest;

import Pages.AboutPage;
import Pages.CartAndCheckoutPage;
import Pages.LoginPage;
import Utils.DriverUtils;
import Utils.ExcelUtils;
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

    @Test(dataProvider = "YourInfo", dataProviderClass = ExcelUtils.class)
    public void SelectCostliestItemAndCheckoutTest(String firstName, String lastName, String postalCode) {

        WebDriver selectCostliestItemAndCheckoutDriver = DriverUtils.initBrowser("chrome");

        LoginPage loginPage = new LoginPage(selectCostliestItemAndCheckoutDriver);
        loginPage.login();

        CartAndCheckoutPage cartAndCheckoutPage = new CartAndCheckoutPage(selectCostliestItemAndCheckoutDriver);
        cartAndCheckoutPage.addCostliestItemToCart(firstName, lastName, postalCode);

        DriverUtils.tearDown(selectCostliestItemAndCheckoutDriver);

    }

}
