package com.misha.booknetwork.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.ContentDisposition;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.thymeleaf.context.Context;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;


@Service
@RequiredArgsConstructor
public class EmailService {

        private final JavaMailSender javaMailSender;
        private  final SpringTemplateEngine templateEngine;

        @Async
        public void sendEmail(
                String to,
                String username,
                EmailTemplateName emailTemplateName,
    String confirmationUrl,
    String activationCode,
    String subject
        ) throws MessagingException {
            String templateName;
            if(emailTemplateName == null) {
                templateName = "confirm-email";
            } else {
                templateName = emailTemplateName.getName();
            }

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MULTIPART_MODE_MIXED,
                    UTF_8.name()
            );
            Map<String, Object> properties = new HashMap<>();
            properties.put("username", username);
            properties.put("confirmationUrl", confirmationUrl);
            properties.put("activation_code", activationCode);

            Context context = new Context();
            context.setVariables(properties);

            messageHelper.setFrom("mishamay@gmail.com");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            String template = templateEngine.process(templateName, context);

            messageHelper.setText(template, true);
            javaMailSender.send(mimeMessage);

        }







}
