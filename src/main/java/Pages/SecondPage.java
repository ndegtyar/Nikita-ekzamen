package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SecondPage {

    public SelenideElement emailInput() {
        return $("[id='newsletter-input']").as("поле для воода почты");
    }

    public SelenideElement invalidEmail() {
        return $(".alert.alert-danger").as("сообщение об ошибке");
    }

    public SelenideElement submitLetter() {
        return $("[name='submitNewsletter']").as("кнопка подтверждения ввода почты");
    }

}
