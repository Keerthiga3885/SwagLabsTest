package Pages;

import Utils.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartAndCheckoutPage extends Base {

    WebDriver driver;

    @FindAll(@FindBy(xpath = "//*[@class='inventory_item_price']"))
    List<WebElement> priceOfItems;

    @FindBy(xpath = "//button[text()='Add to cart']")
    WebElement btnAddToCart;

    @FindBy(id = "shopping_cart_container")
    WebElement btnCart;

    @FindBy(xpath = "//span[text()='Your Cart']")
    WebElement hdrCart;

    @FindBy(id = "checkout")
    WebElement btnCheckout;

    @FindBy(xpath = "//span[text()='Checkout: Your Information']")
    WebElement hdrCheckout;

    @FindBy(id = "first-name")
    WebElement txtFirstName;

    @FindBy(id = "last-name")
    WebElement txtLastName;

    @FindBy(id = "postal-code")
    WebElement txtPostalCode;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//span[text()='Checkout: Overview']")
    WebElement hdrOverview;

    @FindBy(xpath = "//*[@class='summary_info_label summary_total_label']")
    WebElement lblTotalPrice;

    public CartAndCheckoutPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public void addCostliestItemToCart(String firstName, String lastName, String postalCode) {

        //Selecting the costliest item and add to cart
        double highestPrice = 0;
        waitUntilAllElementsVisible(driver, 10, priceOfItems);

        for (WebElement item : priceOfItems) {

            double temp = Double.parseDouble(item.getText().split("\\$")[1]);

            if (temp > highestPrice) {
                highestPrice = temp;
            }

        }

        driver.findElement(By.xpath("//*[@class='inventory_item_price' and text()='" + highestPrice + "']/preceding::div[3]/a")).click();

        waitUntilPageLoad(driver,10,driver.getCurrentUrl());
        waitToClick(driver, 10, btnAddToCart);
        btnAddToCart.click();

        waitToClick(driver, 5, btnCart);
        btnCart.click();

        //Add info in cart and checkout
        waitUntilPageLoad(driver, 10, driver.getCurrentUrl());
        waitUntilElementVisible(driver, 5, hdrCart);
        waitToClick(driver,20,btnCheckout);
        btnCheckout.click();

        waitUntilPageLoad(driver, 10, driver.getCurrentUrl());
        waitUntilElementVisible(driver, 5, hdrCheckout);

        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtPostalCode.sendKeys(postalCode);

        waitToClick(driver,10,btnContinue);
        btnContinue.click();

        waitUntilPageLoad(driver, 10, driver.getCurrentUrl());
        waitUntilElementVisible(driver, 5, hdrOverview);

        if(!lblTotalPrice.getText().matches(".*\\$\\d+\\.\\d{2}.*")){
            throw new AssertionError(" Total Price not in $xx.yy format");
        }

    }

}
