package project.irfanadios.emailservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    // @Autowired
    // private EmailService emailService;

    @KafkaListener(topics = "auth-email-topics", groupId = "emailgroup")
    public void consume(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode map = mapper.readTree(message);

            String recepient = map.get("recepient").toString();
            String subject = map.get("subject").toString();
            String body = map.get("body").toString();
            logger.info(recepient);
            logger.info(subject);
            logger.info(body);

            // emailService.sendSimpleMail(recepient, subject, body);
        } catch (JsonProcessingException e) {
            logger.error("Mail service error: {}", e.getMessage());
        }
    }
}
