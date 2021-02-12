package ru.netology.web.data;
import com.github.javafaker.Faker;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    public static FillingOfForm registrationByFaker(String locale){
        Faker faker = new Faker(new Locale("ru"));
        return new FillingOfForm(
                faker.address().city(),
                faker.date().future(7,3,TimeUnit.DAYS),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(11)
        );
    }
}

