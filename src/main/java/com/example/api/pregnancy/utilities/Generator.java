package com.example.api.pregnancy.utilities;

import com.example.api.pregnancy.models.SmsCode;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class Generator {

    public String generateFiveDigits() {
        Random random = new Random(System.currentTimeMillis());
        return String.valueOf(((1 + random.nextInt(2)) * 10000 + random.nextInt(10000)));
    }

    public String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static SmsCode createSMSCode() {
        //Introducing commons Lang package
        Random r = new Random(System.currentTimeMillis());
        int code = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return new SmsCode(String.valueOf(code), 60);
    }
}
