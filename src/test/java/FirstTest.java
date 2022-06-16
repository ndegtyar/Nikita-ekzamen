import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@Owner("ndegtyar")
public class FirstTest {

    @BeforeEach
    public void beforeTest() {
        open("http://automationpractice.com/index.php");
        TestPages.mainPage.headImg()
                .shouldBe(visible);
    }

    @Test
    @DisplayName("Быстрое добавление товара в корзину")
    public void FirstTest() {
        step("Нажать на кнопку быстрого добавления в корзину и проверить сообщение в модальном окне", () -> {
            TestPages.mainPage.productList()
                    .scrollTo();
            TestPages.mainPage.productList()
                    .shouldBe(visible);
            TestPages.mainPage.productNumber()
                    .click();
            TestPages.mainPage.addProductInCart()
                    .shouldHave(text("Product successfully added to your shopping cart"));
        });
    }

    @Story("Ввод почты")
    @MethodSource("negativeChecks")
    @ParameterizedTest(name = "{0}")
    @DisplayName("Негативные проверки ввода почты")
    public void negativeEmailTest(String type, String nameEmail) {
        step("Ввести в поле для почты данные", () -> {
            TestPages.secondPage.emailInput()
                    .scrollTo()
                    .sendKeys(nameEmail);
        });

        step("Нажать кнопку подтверждения и проверить сообщение об ошибке", () -> {
            TestPages.secondPage.submitLetter()
                    .click();
            TestPages.secondPage.invalidEmail()
                    .shouldHave(text("Newsletter : Invalid email address."));
        });
    }

    static Stream<Arguments> negativeChecks() {
        return Stream.of(
                arguments(
                        "вводим цифры",
                        "1571515",
                        "Newsletter : Invalid email address."
                ),
                arguments(
                        "почта с пробелами",
                        "   ываываы@mail.ru",
                        "Newsletter : Invalid email address."
                ),
                arguments(
                        "почта без домена",
                        "cat@mail",
                        "Newsletter : Invalid email address."
                )
        );
    }
}

