package com.ou.utils;

import org.springframework.core.env.Environment;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


public class MailUtil {
    private Environment env;

    private Properties properties;

    public MailUtil(Environment environment, Properties properties) {
        env = environment;
        this.properties = properties;
        String s = env.getProperty("mail.smtp.auth");
        this.properties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        this.properties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        this.properties.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        this.properties.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
        this.properties.put("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));
        this.properties.put("mail.username", env.getProperty("mail.username"));
        this.properties.put("mail.password", env.getProperty("mail.password"));
    }

    public void sendMail(String mail, String subject, String content) throws MessagingException {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("mail.username"),
                        env.getProperty("mail.password"));
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(properties.getProperty("mail.username")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }
}
