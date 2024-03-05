package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Base {

    // Wait until element is clickable
    public void waitToClick(WebDriver driver, int time, WebElement element) {

        new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.elementToBeClickable(element));

    }

    // Wait until all elements are visible
    public void waittoVisibleAllElements(WebDriver driver, int time, List<WebElement> elements) {

        new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOfAllElements(elements));

    }

}
