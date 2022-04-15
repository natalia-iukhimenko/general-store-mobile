package ru.iukhimenko.gstoreautomation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.iukhimenko.gstoreautomation.MobileDriver;

public class MobileBasePage {
    AndroidDriver<MobileElement> driver = MobileDriver.getInstance().getDriver();

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    protected AndroidElement toolBarTitleElement;

    public MobileBasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void scrollTo(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))");
    }

    protected boolean isChecked(WebElement element) {
        return element.getAttribute("checked").equals("true");
    }
}
