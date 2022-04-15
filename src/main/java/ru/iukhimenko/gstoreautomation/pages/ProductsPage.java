package ru.iukhimenko.gstoreautomation.pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import ru.iukhimenko.gstoreautomation.util.WaitUtil;

import java.util.List;

public class ProductsPage extends MobileBasePage {
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
    private AndroidElement backButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private AndroidElement cartButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/counterText")
    private AndroidElement countInCartElement;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<AndroidElement> productNameElements;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<AndroidElement> productPriceElements;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    private List<AndroidElement> addToCartButtons;

    public ProductsPage() {
        WaitUtil.waitForToolbarTitleToBe(toolBarTitleElement, "Products", 5);
    }

    public StartPage returnToStartScreen() {
        backButton.click();
        return new StartPage();
    }

    private int getProductElementIndexByName(String productName) {
        scrollTo(productName);
        for (int i = 0; i < productNameElements.size(); i++) {
            if (productNameElements.get(i).getText().equals(productName)) {
                return i;
            }
        }
        throw new AssertionError("Product '" + productName + "' not found");
    }

    public ProductsPage addProductToCart(String productName) {
        int index = getProductElementIndexByName(productName);
        if (!isProductAddedToCart(index)) {
            addToCartButtons.get(index).click();
        }
        return this;
    }

    public ProductsPage addProductsToCart(List<String> productNames) {
        for (String productName : productNames) {
            addProductToCart(productName);
        }
        return this;
    }

    public ProductsPage removeProductsFromCart(List<String> productNames) {
        for (String productName : productNames) {
            removeProductFromCart(productName);
        }
        return this;
    }

    private boolean isProductAddedToCart(int productElementIndex) {
        return addToCartButtons.get(productElementIndex).getText().equals("ADDED TO CART");
    }

    public ProductsPage removeProductFromCart(String productName) {
        int index = getProductElementIndexByName(productName);
        if (isProductAddedToCart(index)) {
            addToCartButtons.get(index).click();
        }
        return this;
    }

    public int getProductsCountInCart() {
        if (countInCartElement.getText().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(countInCartElement.getText());
        }
    }

    public boolean isCartProductCounterDisplayed() {
        return countInCartElement != null && countInCartElement.isDisplayed();
    }
}
