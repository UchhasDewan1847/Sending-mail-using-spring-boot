package com.agent47.mailsendingapp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service

public class MailSendingService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailProperties mailProperties;
// You can use SimpleMailMessage instead of MimeMessage for basic and simple mail messages
    public void mailWithAttachment(
            String to,
            String body,
            String subject,
            String filepath
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setFrom(mailProperties.getUsername());
        String htmlcontent= "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Fancy Email</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            color: #333;\n" +
                "            margin: 0;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 2px 5px rgba(0,0,0,0.1);\n" +
                "        }\n" +
                "        h1 {\n" +
                "            color: #333;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        p {\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.6;\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "        .button {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #007bff;\n" +
                "            color: #fff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 3px;\n" +
                "            transition: background-color 0.3s ease;\n" +
                "        }\n" +
                "        .button:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Welcome to Our Newsletter!</h1>\n" +
                "        <p>\n" +
                "            Thank you for subscribing to our newsletter. We have exciting news and updates to share with you.\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            Stay tuned for our upcoming events and special offers!\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            <a href=\"#\" class=\"button\">Read More</a>\n" +
                "        </p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        helper.setText(body,htmlcontent);

        FileSystemResource file= new FileSystemResource(new File(filepath));
        helper.addAttachment(Objects.requireNonNull(file.getFilename()),file);

        mailSender.send(mimeMessage);
        System.out.println("mail sent without any error");

    }

}
