package OnlineStore;

import Base.BaseTest;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestOnlineStore extends BaseTest {

    @Test
    public void verifyLoginForm(){
        try{
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
            String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"India\").instance(0))";
            driver.findElementByAndroidUIAutomator(scrollElement).click();
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Amit");
            driver.hideKeyboard();
            driver.findElementByAndroidUIAutomator("text(\"Female\")").click();
            AndroidElement shopButton = driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");
            if(shopButton.isEnabled())
                shopButton.click();
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
            AndroidElement cart = driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart");
            if(!cart.isDisplayed()){
                Assert.fail("login unsuccessful");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void verifyToastMessageInLoginForm() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            AndroidElement shopButton = driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");
            if (shopButton.isEnabled())
                shopButton.click();
            String expectedToastMsg = "Please enter your name";
            String toastMsg = driver.findElementByXPath("//android.widget.Toast").getAttribute("name");
            Assert.assertEquals(toastMsg, expectedToastMsg, "Either Toast Message is not Appear or Wrong Message shown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verifyNewItemAddedInCart(){
        try{
            loginToGeneralStore();
            String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Jordan Lift Off\").instance(0))";
            driver.findElementByAndroidUIAutomator(scrollElement).click();
            int count = driver.findElementsById("com.androidsample.generalstore:id/productName").size();
            for(int i=0;i<count;i++) {
                String text = driver.findElementsById("com.androidsample.generalstore:id/productName").get(i).getText();
                if (text.equalsIgnoreCase("Jordan Lift Off")) {
                    driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
                    break;
                }
            }
            //Click on Cart and validate
            driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
            String cartItem = driver.findElementByAndroidUIAutomator("Jordan Lift Off").getText();
            Assert.assertEquals(cartItem, "Jordan Lift Off", "Added item is not available in cart");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void verifyTotalSumWhenMultipleItemsAddedToCart(){
        loginToGeneralStore();
        String[] productList= {"PG 3", "Jordan Lift Off"};
        for (String product : productList) {
            addSpecificItemToCart(product);
        }
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
        List<AndroidElement> prices = driver.findElementsById("com.androidsample.generalstore:id/productPrice");
        double totalSum = 0.0;
        for (AndroidElement price : prices){
            totalSum+= Double.parseDouble(price.getText());
        }
        String totalAmount = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
        Assert.assertEquals(totalSum, Double.parseDouble(totalAmount), "sum of Prices is not matching with total amount");
    }

    private void addSpecificItemToCart(String ProductName){
        String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+ ProductName + "\").instance(0))";
        driver.findElementByAndroidUIAutomator(scrollElement);
        int count = driver.findElementsById("com.androidsample.generalstore:id/productName").size();
        for(int i=0;i<count;i++) {
            String text = driver.findElementsById("com.androidsample.generalstore:id/productName").get(i).getText();
            if (text.equalsIgnoreCase(ProductName)) {
                driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
                break;
            }
        }
    }

    private void loginToGeneralStore()
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
        String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"India\").instance(0))";
        driver.findElementByAndroidUIAutomator(scrollElement).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Testy");
        driver.hideKeyboard();
        driver.findElementByAndroidUIAutomator("text(\"Female\")").click();
        AndroidElement shopButton = driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");
        if(shopButton.isEnabled())
            shopButton.click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        AndroidElement cart = driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart");
        if(!cart.isDisplayed()){
            Assert.fail("login unsuccessful");
        }
    }


}
