package org.guiders.api.util;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Account;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GMailSender {

    private final JavaMailSender mailSender;
    private final static String FROM_ADDRESS = "chtlstjd01@gmail.com";

    public void sendPassword(String receiveAddress) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiveAddress);
        message.setFrom(FROM_ADDRESS);
        message.setSubject("테스트");
        message.setText("테스트 내용");

        mailSender.send(message);
    }

}
