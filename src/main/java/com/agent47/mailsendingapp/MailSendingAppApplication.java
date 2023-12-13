package com.agent47.mailsendingapp;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MailSendingAppApplication {
	@Autowired
	private MailSendingService mailSendingService;

	public static void main(String[] args) {
		SpringApplication.run(MailSendingAppApplication.class, args);

	}
	@EventListener(ApplicationReadyEvent.class)
	public  void sendMail()throws MessagingException {
		mailSendingService.mailWithAttachment(
				"uchhasdewan@gmail.com",
				"This is just testing the mail sending confirmation",
				"I guess it worked",
				"E:/spring-security-6-architecture.png"
		);
	}
}
