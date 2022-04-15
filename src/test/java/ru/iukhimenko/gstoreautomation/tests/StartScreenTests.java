package ru.iukhimenko.gstoreautomation.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.iukhimenko.gstoreautomation.BaseTest;
import ru.iukhimenko.gstoreautomation.enums.Gender;
import ru.iukhimenko.gstoreautomation.pages.StartPage;

import static org.assertj.core.api.Assertions.assertThat;

public class StartScreenTests extends BaseTest {
    @ParameterizedTest
    @CsvSource({"Australia, Tracey"})
    void fieldsNotClearedOnReturn(String country, String name) {
        Gender expectedGender = Gender.FEMALE;
        StartPage startPage = new StartPage()
                .goToProducts(country, name, expectedGender)
                .returnToStartScreen();
        assertThat(startPage.getSelectedCountryValue()).isEqualTo(country);
        assertThat(startPage.getNameValue()).isEqualTo(name);
        assertThat(startPage.getSelectedGender()).isEqualTo(expectedGender);
    }

    @ParameterizedTest
    @ValueSource(strings = "Albania")
    void canNotStartShoppingWithoutName(String country) {
        StartPage page = new StartPage().selectCountry(country);
        page.startShopping();
        assertThat(page.isOnStartScreen()).isTrue();
    }
}