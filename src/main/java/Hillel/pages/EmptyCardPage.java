package Hillel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class EmptyCardPage {

    private WebDriver driver;

    @FindBy(xpath = "//p[@class='alert alert-warning'][contains(text(),'Your shopping cart is empty.')]")
    private WebElement checkoutMessage;

    public EmptyCardPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }

    public String getCheckoutMessage(){
        return checkoutMessage.getText();
    }
}
