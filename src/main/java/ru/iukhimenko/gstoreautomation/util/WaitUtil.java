package ru.iukhimenko.gstoreautomation.util;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.iukhimenko.gstoreautomation.MobileDriver;

@UtilityClass
public class WaitUtil {
    private final int DEFAULT_TIMER_SEC = 60;

    public void waitFor(WebElement element, int timer) {
        WebDriver driver = MobileDriver.getInstance().getDriver();
        new WebDriverWait(driver, timer)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public void waitForToolbarTitleToBe(WebElement element, String titleText) {
        waitForToolbarTitleToBe(element, titleText, DEFAULT_TIMER_SEC);
    }

    public void waitForToolbarTitleToBe(WebElement element, String titleText, int timer) {
        WebDriver driver = MobileDriver.getInstance().getDriver();
        new WebDriverWait(driver, timer)
                .until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElement(element, titleText)));
    }
}
