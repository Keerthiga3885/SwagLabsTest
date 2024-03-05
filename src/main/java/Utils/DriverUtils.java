package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtils {

    // Launch browser
    public static WebDriver initBrowser(String browserName) {

        WebDriver driver;

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        return driver;

    }

    // Close browser
    public static void tearDown(WebDriver driver) {
        driver.quit();
    }

}
