package ru.iukhimenko.gstoreautomation.tests;

import org.junit.jupiter.api.Test;
import ru.iukhimenko.gstoreautomation.BaseTest;
import ru.iukhimenko.gstoreautomation.enums.Gender;
import ru.iukhimenko.gstoreautomation.pages.ProductsPage;
import ru.iukhimenko.gstoreautomation.pages.StartPage;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductsScreenTests extends BaseTest {
    private String defaultName = "Adam";
    private String defaultCountry = "Armenia";
    private Gender defaultGender = Gender.MALE;
    private List<String> products = Arrays.asList("Converse All Star", "PG 3");

    @Test
    void checkCountInCartIncreasedOnProductAdd() {
        ProductsPage productsPage = new StartPage()
                .goToProducts(defaultCountry, defaultName, defaultGender)
                .addProductsToCart(products);
        assertThat(productsPage.getProductsCountInCart()).isEqualTo(products.size());
    }
}