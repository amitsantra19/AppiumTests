import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        File src = new File("src");
        File demoApk = new File(src, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AmitEmulator");
        capabilities.setCapability(MobileCapabilityType.APP, demoApk.getAbsolutePath());
        try {
        AndroidDriver<AndroidElement> driver = new AndroidDriver(new URL("https://127.0.0.1:4723/wb/hub"), capabilities);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void MyFirstTest()
    {

    }

}
