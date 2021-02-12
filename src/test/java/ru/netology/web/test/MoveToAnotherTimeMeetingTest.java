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
        form.$("[data-test-id='city'] [placeholder='Город']").setValue(DataGenerator.registrationByFaker("ru")
                .getCity());
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL, "a", Keys.CONTROL, "x");
        Date dateFromFaker = DataGenerator.registrationByFaker("ru").getDateOfMeeting();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        form.$("[data-test-id='date'] .input__control").setValue((df.format(dateFromFaker)).toString());
        form.$("[name='name']").setValue(DataGenerator.registrationByFaker("ru").getFullName());
        form.$("[name='phone']").setValue(DataGenerator.registrationByFaker("ru").getPhone().toString());
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL, "a", Keys.CONTROL, "x");
        Date anotherDateFromFaker = DataGenerator.registrationByFaker("ru").getDateOfMeeting();
        form.$("[data-test-id='date'] .input__control").setValue((df.format(anotherDateFromFaker)).toString());
        form.$(".button").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(byText("Перепланировать")).click();
        String dateChecking = df.format(anotherDateFromFaker);
        String beginningOfText = "Встреча успешно запланирована на ";
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text(beginningOfText + dateChecking));
    }
}
