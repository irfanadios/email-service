package project.irfanadios.emailservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.irfanadios.emailservice.dto.SimpleEmailDto;

@Service
public class KafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private EmailService emailService;

    private ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "auth-email-topics", groupId = "emailgroup")
    public void consume(String emailJson) {
        logger.info("Email Dto: {}", emailJson);

        try {
            SimpleEmailDto emailDto = mapper.readValue(emailJson, SimpleEmailDto.class);
            emailService.sendSimpleMail(emailDto.getRecepient(), emailDto.getSubject(), emailDto.getBody());
        } catch (JsonProcessingException e) {
            logger.error("Error Json Process: {}", e.getMessage());
        }

    }
}
