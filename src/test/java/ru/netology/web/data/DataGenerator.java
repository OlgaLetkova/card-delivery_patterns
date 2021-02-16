package ru.netology.web.data;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    public static FillingOfForm registrationByFaker(){
        Faker faker = new Faker(new Locale("ru"));
        Date randomDate = faker.date().future(7,3,TimeUnit.DAYS);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return new FillingOfForm(
                faker.address().city(),
                df.format((randomDate)),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(11)
        );
    }
}
