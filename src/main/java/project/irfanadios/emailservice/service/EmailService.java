package project.irfanadios.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.test.name}")
    private String emailTestName;

    public void sendSimpleMail(String recepient, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setSubject(subject);
        mail.setTo(recepient);
        mail.setFrom(emailTestName);
        mail.setText(body);

        javaMailSender.send(mail);
    }
}
