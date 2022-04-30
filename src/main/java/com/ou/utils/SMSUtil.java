package com.ou.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.core.env.Environment;

import java.util.Objects;

public class SMSUtil {
    public Environment env;

    public SMSUtil(Environment environment) {
        env=environment;
        Twilio.init(Objects.requireNonNull(env.getProperty("sms.account_sid")),
                Objects.requireNonNull(env.getProperty("sms.auth_token")));
    }

    public void sendMessage(String phoneNumber, String content) {

        Message.creator(new PhoneNumber(env.getProperty("sms.phone_number")),
                new PhoneNumber(phoneNumber), content).create();
    }
}
