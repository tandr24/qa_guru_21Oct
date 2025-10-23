import com.codeborne.selenide.Configuration;
import data.Language;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ParameterizedTests {
    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
    }

    @ValueSource(strings = {
            "standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"
    })
    @ParameterizedTest(name = "Для user {0} логин работает")
    void logInWorksForAllUsers(String userValue) {
        String siteValue = "https://www.saucedemo.com/";
        String passwordValue = "secret_sauce";

        open(siteValue);
        $("#user-name").setValue(userValue);
        $("#password").setValue(passwordValue);
        $("#login-button").click();
        $("#page_wrapper").shouldHave(text("Swag Labs"));


    }

    @ParameterizedTest(name = " {0} ")
    @EnumSource(Language.class)
    @DisplayName("Сайт отображает языки корректно")
    void sushiSiteShouldShowCorrectTextDependsOnLang(Language language) {
        open("https://yumesushi.rs/");
        $("header select#language").selectOptionByValue(language.value);
        $("div.MuiGrid2-root.MuiGrid2-container.MuiGrid2-direction-xs-row.MuiGrid2-spacing-xs-1.burger-category__grid.css-1rkz8o4").shouldHave(text(language.description));

    }

    @CsvSource(value = {
            "Alex Brain, example@mail.com, currentAddress1, permanentAddress2",
            "Selen Yoga, long@mail.com, currentAddress1, permanentAddress2",
            "Fin Pin, short@mail.com, currentAddress1, permanentAddress2"
    })
    @ParameterizedTest(name = "Для каждого user {0} заполняется форма")
    void eachFieldIsFilledInTextBox(String name, String email, String currentAddress, String permanentAddress) {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();
        $("#output").shouldHave(text(name));
        $("#output").shouldHave(text(email));
        $("#output").shouldHave(text(currentAddress));
        $("#output").shouldHave(text(currentAddress));

    }


}
