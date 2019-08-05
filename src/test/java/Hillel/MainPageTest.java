package Hillel;


import Hillel.pages.MainPage;
import Hillel.utils.TestHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Hillel.pages.EmptyCardPage;

public class MainPageTest extends BaseTest {

    private MainPage mainPage;
    private EmptyCardPage emptyCardPage;


    @BeforeMethod
    public void setupMainPage() {
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
        Assert.assertEquals(actualResult5, expectedResult5, "getTax");
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
        emptyCardPage = new EmptyCardPage(driver);
        Assert.assertTrue(


                emptyCardPage.getCheckoutMessage().contains("Your shopping cart is empty."));
                     //   mainPage.clickDeleteButton());
        //    .toCheckoutDeletedProduct();
     //   emptyCardPage.getCheckoutMessage();
        //   .isErrorMesage()
        //     );
    }

    @AfterMethod
    public void cleanup() {
        driver.manage().deleteAllCookies();
        TestHelper.sleep5Seconds();
        driver.close();
    }

}