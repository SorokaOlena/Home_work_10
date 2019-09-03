package Hillel;


import Hillel.pages.MainPage;
import Hillel.utils.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Hillel.pages.EmptyCardPage;
import org.openqa.selenium.WebDriver;

public class MainPageTest extends BaseTest {

    private MainPage mainPage;
    private EmptyCardPage emptyCardPage;
    private TestHelper testHelper;

    String expectedResult4 = "$56.00";



    @BeforeMethod
    public void setupMainPage() {
        emptyCardPage = new EmptyCardPage(driver);
        mainPage = new MainPage(driver);
        mainPage.fillSearch("Blouse")
                .clickSearchButton()
                .clickListView()
                .clickAddToCart()
                .clickProceedToCheckout()
                .clickAddButton()
                .clickProceedToCheckout2();
    }

    @Test
    public void testGetCardTotal() {
        String actualResult1 = mainPage.getCardTotal();
        String expectedResult1 = "$54.00";
        Assert.assertEquals(expectedResult1, actualResult1, "getCardTotal");
    }

    @Test
    public void testGetTotalProduct() {
        String expectedResult1 = "$54.00";
        String actualResult2 = mainPage.getTotalProduct();
        Assert.assertEquals(expectedResult1, actualResult2, "getTotalProduct");
    }

    @Test
    public void testGetTotalShipping() {
        String actualResult3 = mainPage.getTotalShipping();
        String expectedResult3 = "$2.00";
        Assert.assertEquals(expectedResult3, actualResult3, "getTotalShipping");
    }

    @Test
    public void testGetTotalPriceWithoutTax() {
        String actualResult4 = mainPage.getTotalPriceWithoutTax();
        String expectedResult4 = "$56.00";
        Assert.assertEquals(expectedResult4, actualResult4, "getTotalPriceWithoutTax");
    }

    @Test
    public void testGetTax() {
        String actualResult5 = mainPage.getTax();
        String expectedResult5 = "$0.00";
        Assert.assertEquals(expectedResult5,actualResult5,  "getTax");
    }

    @Test
    public void testGetTotalPrice() {
        String actualResult6 = mainPage.getTotalPrice();
        String expectedResult4 = "$56.00";
        Assert.assertEquals(expectedResult4, actualResult6, "getTotalPrice");
    }

    @Test
    public void testToCheckoutDeletedProduct() {

        mainPage.clickDeleteButton();
        testHelper = new TestHelper();
        testHelper.toWait("//*[text()[contains(.,'Your shopping cart is empty.')]]",driver);

        System.out.println("text is : " + emptyCardPage.getCheckoutMessage());
        Assert.assertTrue(
                emptyCardPage.getCheckoutMessage().contains("Your shopping cart is empty."));

    }

    @AfterMethod
    public void cleanup() {
        driver.manage().deleteAllCookies();
        TestHelper.sleep5Seconds();
        driver.close();
    }

}