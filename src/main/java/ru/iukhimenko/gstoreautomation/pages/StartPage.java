package ru.iukhimenko.gstoreautomation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import ru.iukhimenko.gstoreautomation.MobileDriver;
import ru.iukhimenko.gstoreautomation.enums.Gender;
import ru.iukhimenko.gstoreautomation.util.WaitUtil;

import java.util.List;

public class StartPage extends MobileBasePage {
    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private AndroidElement countryDropdown;

    @AndroidFindBy(id = "android:id/text1")
    private AndroidElement countryValueElement;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private AndroidElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private AndroidElement maleGenderRadioButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private AndroidElement femaleGenderRadioButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private AndroidElement letsShopButton;

    public StartPage() {
        WaitUtil.waitFor(toolBarTitleElement, 60);
    }

    // region Actions
    private List<MobileElement> getCountryDropdownOptions() {
        return MobileDriver.getInstance().getDriver().findElements(By.id("android:id/text1"));
    }

    public StartPage selectCountry(String country) {
        countryDropdown.click();
        scrollTo(country);
        getCountryDropdownOptions().stream()
                .filter(v -> v.getText().equals(country))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Country '" + country + "' not found in dropdown"))
                .click();
        return this;
    }

    public StartPage enterName(String name) {
        nameField.sendKeys(name);
        return this;
    }

    public StartPage selectGender(Gender gender) {
        if (gender.equals(Gender.FEMALE)) {
            femaleGenderRadioButton.click();
        } else {
            maleGenderRadioButton.click();
        }
        return this;
    }

    public StartPage startShopping() {
        letsShopButton.click();
        return this;
    }

    public ProductsPage goToProducts(String country, String name, Gender gender) {
        selectCountry(country);
        enterName(name);
        selectGender(gender);
        startShopping();
        return new ProductsPage();
    }

    public String getSelectedCountryValue() {
        return countryValueElement.getText();
    }

    public String getNameValue() {
        return nameField.getText();
    }

    public Gender getSelectedGender() {
        if (isChecked(maleGenderRadioButton)) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    public boolean isOnStartScreen() {
        return toolBarTitleElement.getText().equals("General Store");
    }
    // endregion
}
