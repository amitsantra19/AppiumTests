package Base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.URL;

public class BaseTest {
    public AndroidDriver<AndroidElement> driver = null;
    @BeforeSuite
    public void setUp() {
        startServer();
        File src = new File("src");
        File demoApk = new File(src, "General-Store.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // To set up in Emulator
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AmitEmulator:5554");
        // To setup in real device
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        capabilities.setCapability(MobileCapabilityType.APP, demoApk.getAbsolutePath());
        try {
        driver = new AndroidDriver<>(new URL("http://127.0.1.1:4723/wd/hub"), capabilities);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void testTearDown(){
        driver.quit();
        stopServer();
    }

    public void startServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.1.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
            Thread.sleep(10000);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
