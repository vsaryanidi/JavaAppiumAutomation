package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String
    PLATFORM_IOS="ios",
    PLATFORM_ANDROID="android"
            ;

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriverByPlatformEnv();
        this.rotateScreenPortrait();

    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {

        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(Duration seconds) {

        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {

        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {

            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "C:\\Users\\vsary\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
            capabilities.setCapability("orientation", "PORTRAIT");

        } else if (platform.equals(PLATFORM_IOS)) {

            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("platformVersion", "13.6");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("app", "/Users/karma/Documents/GitHub/JavaAppiumAutomation/apks/Wikipedia.app");
            capabilities.setCapability("orientation", "PORTRAIT");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv() throws Exception {

        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        if (platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        }
        else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(AppiumURL),capabilities);
        }
        else {
            throw new Exception("Cannot get run driver from env variable. Driver value " + driver);
        }
        return driver;
    }
}
