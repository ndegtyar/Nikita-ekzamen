package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public SelenideElement productList() {
        return $("#homefeatured").as("блок с товарами");
    }

    public SelenideElement productNumber() {
        return $("[data-id-product='2']").as("второй товар в блоке");
    }

    public SelenideElement addProductInCart() {
        return $(".layer_cart_product").as("модальное окно о добавлении товара");
    }

    public SelenideElement headImg() {
        return $(".logo.img-responsive").as("основной логотип сайта");
    }
}
