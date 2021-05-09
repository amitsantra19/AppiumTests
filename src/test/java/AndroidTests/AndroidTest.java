package AndroidTests;
import Base.BaseTest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.testng.annotations.Test;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

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


    //Using UiAutomator
    @Test
    public void verifyNavigationByUiAutomator(){
        try{
            driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
            Thread.sleep(1000);
            driver.findElementByAndroidUIAutomator("text(\"Animation\")").click();
            Thread.sleep(1000);
            AndroidElement transact =  driver.findElementByAndroidUIAutomator("text(\"3D Transition\")");
            if (transact.isEnabled())
                transact.click();
            Thread.sleep(1000);
            int size = driver.findElementsByAndroidUIAutomator("new UiSelector().enabled(true)").size();
            System.out.println(size);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void verifyMobileGestureTapByLocators(){
        try{
            driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
            Thread.sleep(1000);
            //Tap on mobile element
            TouchAction<?> touchAction = new TouchAction<>(driver);
            AndroidElement dateWidget = driver.findElementByAndroidUIAutomator("text(\"Date Widgets\")");
            touchAction.tap(tapOptions().withElement(element(dateWidget))).perform();
            Thread.sleep(1000);
            AndroidElement inlineMenu = driver.findElementByAndroidUIAutomator("text(\"2. Inline\")");
            touchAction.tap(tapOptions().withElement(element(inlineMenu))).perform();
            Thread.sleep(1000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
