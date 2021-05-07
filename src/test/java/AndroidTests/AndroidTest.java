package AndroidTests;
import Base.BaseTest;

import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;

public class AndroidTest extends BaseTest {

    @Test
    public void verifyNavigationByMenuItem() throws InterruptedException {
         /*
         Xpath Writing syntax
         //tagName[@attribute='value']
         //android.widget.TextView[@attribute='']
          */
        driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
        Thread.sleep(1000);
        driver.findElementByClassName("android.widget.CheckBox").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
        Thread.sleep(1000);
        driver.findElementByClassName("android.widget.EditText").sendKeys("Hello Appium");
        Thread.sleep(1000);
        driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
        Thread.sleep(1000);
    }
}
