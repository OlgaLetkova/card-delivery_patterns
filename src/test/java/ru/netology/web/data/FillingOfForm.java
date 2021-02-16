package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor

public class FillingOfForm {
private final String city;
private String dateOfMeeting;
private final String fullName;
private final String phone;
}
