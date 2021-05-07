package AndroidTests;
import Base.BaseTest;
import org.testng.annotations.Test;

public class AndroidTest extends BaseTest {
    @Test
    public void verifyNavigationByMenuItem() {
        try {
            driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
            Thread.sleep(1000); // static wait, later modify with dynamic wait
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
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
