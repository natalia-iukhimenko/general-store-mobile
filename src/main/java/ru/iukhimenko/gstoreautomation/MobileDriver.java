package ru.iukhimenko.gstoreautomation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static ru.iukhimenko.gstoreautomation.MobileConfiguration.CONFIG;

@Log4j2
public class MobileDriver {
    private static MobileDriver instance;
    private final ThreadLocal<AndroidDriver<MobileElement>> androidDriver = new ThreadLocal<>();

    public static MobileDriver getInstance() {
        if (instance == null) {
            instance = new MobileDriver();
        }
        return instance;
    }

    public void setDriver() {
        File appPath = new File(CONFIG.appName());
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(DEVICE_NAME, CONFIG.deviceName());
        capabilities.setCapability(AUTOMATION_NAME, CONFIG.automationName());
        capabilities.setCapability(APP, appPath.getAbsolutePath());
        try {
            androidDriver.set(new AndroidDriver<>(new URL(CONFIG.mobileHubUrl()), capabilities));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public AndroidDriver<MobileElement> getDriver() {
        return androidDriver.get();
    }

    public void closeDriver() {
        try {
            getDriver().quit();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }
    }
}
