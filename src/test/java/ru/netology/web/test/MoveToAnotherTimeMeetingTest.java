package ru.netology.web.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataGenerator;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoveToAnotherTimeMeetingTest {
    @Test
    void shouldCheckOfReScheduleMeetingAtAnotherDate() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='city'] [placeholder='Город']").setValue(DataGenerator.registrationByFaker()
                .getCity());
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL, "a", Keys.CONTROL, "x");
        form.$("[data-test-id='date'] .input__control").setValue(DataGenerator.registrationByFaker().getDateOfMeeting());
        form.$("[name='name']").setValue(DataGenerator.registrationByFaker().getFullName());
        form.$("[name='phone']").setValue(DataGenerator.registrationByFaker().getPhone().toString());
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL, "a", Keys.CONTROL, "x");
        String anotherDate = DataGenerator.registrationByFaker().getDateOfMeeting();
        form.$("[data-test-id='date'] .input__control").setValue(anotherDate);
        form.$(".button").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(byText("Перепланировать")).click();
        String beginningOfText = "Встреча успешно запланирована на ";
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text(beginningOfText + anotherDate));
    }
}
