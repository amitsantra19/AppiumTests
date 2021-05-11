package AndroidTests;
import Base.BaseTest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.Test;


import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

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
    public void verifyMobileGestureTapByLocators() {
        try {
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
            // Tap on a specific coordinates of an element
            AndroidElement clock5 = driver.findElementByClassName("android.widget.RadialTimePickerView$RadialPickerTouchHelper");
            touchAction.tap(tapOptions().withElement(element(clock5)).withPosition(new PointOption<>().withCoordinates(922,1266))).perform();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Test
        public void verifyMobileGestureLongPress() {
            try {
                driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
                Thread.sleep(1000);
                //Tap on mobile element
                TouchAction<?> touchAction = new TouchAction<>(driver);
                AndroidElement expdList = driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
                touchAction.tap(tapOptions().withElement(element(expdList))).perform();
                Thread.sleep(1000);
                AndroidElement customAdp = driver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")");
                touchAction.tap(tapOptions().withElement(element(customAdp))).perform();
                Thread.sleep(1000);
                AndroidElement peopleNames = driver.findElementByAndroidUIAutomator("text(\"People Names\")");
                touchAction.longPress(longPressOptions().withElement(element(peopleNames)).withDuration(ofSeconds(2))).release().perform();
                AndroidElement sampleAc = driver.findElementByAndroidUIAutomator("text(\"Sample action\")");
                touchAction.tap(tapOptions().withElement(element(sampleAc))).perform();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void VerifyMobileGestureSwipingEvent()
        {
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
                // Hold the object and long press then release the object to another element
                AndroidElement clock12 = driver.findElementByXPath("//*[@content-desc ='12']");
                AndroidElement clock6 = driver.findElementByXPath("//*[@content-desc ='6']");
                touchAction.longPress(longPressOptions().withElement(element(clock12)).withDuration(ofSeconds(2))).moveTo(element(clock6)).release().perform();
                Thread.sleep(1000);
                AndroidElement clock15 = driver.findElementByXPath("//*[@content-desc ='15']");
                AndroidElement clock45 = driver.findElementByXPath("//*[@content-desc ='45']");
                touchAction.longPress(longPressOptions().withElement(element(clock15)).withDuration(ofSeconds(2))).moveTo(element(clock45)).release().perform();
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}
