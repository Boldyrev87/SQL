package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=\"login\"] input");
    private SelenideElement passwordField = $("[data-test-id=\"password\"] input");
    private SelenideElement loginButton = $("[data-test-id=\"action-login\"]");


    public void inputData(DataHelper.AuthInfo info) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        inputData(info);
        return new VerificationPage();
    }

    public void blockNotification() {
        $("[data-test-id='error-notification']").shouldHave(text("Система заблокирована!"));
    }
}
