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

    @FindBy(id = "Checkout")
    WebElement btnCheckout;

    @FindBy(xpath = "//span[text()='Checkout: Your Information']")
    WebElement hdrCheckout;

    @FindBy(id = "first-name")
    WebElement txtFisrtName;

    @FindBy(id = "last-name")
    WebElement txtLastName;

    @FindBy(id = "postal-code")
    WebElement txtPostalCode;

    @FindBy(xpath = "//input[@value='CONTINUE']")
    WebElement btnContinue;

    @FindBy(xpath = "//span[text()='Checkout: Overview']")
    WebElement hdrOverview;

    @FindBy(xpath = "//*[@class='summary_total_label']")
    WebElement lblTotalPrice;

    public CartAndCheckoutPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void addCostliestItemToCart() {

        //Selecting costliest item and add to cart
        double highestPrice = 0;
        waitUntilAllElementsVisible(driver, 10, priceOfItems);

        for (WebElement item : priceOfItems) {

            Double temp = Double.parseDouble(item.getText().split("\\$")[1]);

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

        txtFisrtName.sendKeys("keerthi");
        txtLastName.sendKeys("murugan");
        txtPostalCode.sendKeys("1234");

        waitToClick(driver,10,btnContinue);

        waitUntilPageLoad(driver, 10, driver.getCurrentUrl());
        waitUntilElementVisible(driver, 5, hdrOverview);

        if(!lblTotalPrice.getText().matches(".*\\$\\d+\\.\\d{2}.*")){
            throw new AssertionError(" Total Price not in $xx.yy format");
        }

    }

}
