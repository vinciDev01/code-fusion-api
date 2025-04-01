package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ipnet.gl.code_fusion_api.entity.User;
import ipnet.gl.code_fusion_api.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    @Value("${app.base-url}")
    private String baseUrl;
    
    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    
    @Async
    @Override
    public void sendVerificationEmail(User user, String token) {
        try {
            String verificationUrl = baseUrl + "/auth/verify?token=" + token;
            
            Context context = new Context();
            context.setVariable("name", user.getPrenom() + " " + user.getNom());
            context.setVariable("verificationUrl", verificationUrl);
            
            String content = templateEngine.process("email/verification-email", context);
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Vérification de votre compte Invoice Planner");
            helper.setText(content, true);
            
            mailSender.send(message);
            logger.info("Email de vérification envoyé à : {}", user.getEmail());
            
        } catch (MessagingException e) {
            logger.error("Erreur lors de l'envoi de l'email de vérification", e);
        }
    }
    
    @Async
    @Override
    public void sendVerificationCode(User user, String code) {
        try {
            Context context = new Context();
            context.setVariable("name", user.getPrenom() + " " + user.getNom());
            context.setVariable("verificationCode", code);
            
            String content = templateEngine.process("email/verification-code", context);
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Code de vérification - Code Fusion");
            helper.setText(content, true);
            
            mailSender.send(message);
            logger.info("Email avec code de vérification envoyé à : {}", user.getEmail());
            
        } catch (MessagingException e) {
            logger.error("Erreur lors de l'envoi de l'email avec code de vérification", e);
        }
    }
    
    @Async
    @Override
    public void sendPasswordResetEmail(User user, String token) {
        try {
            String resetUrl = baseUrl + "/auth/reset-password?token=" + token;
            
            Context context = new Context();
            context.setVariable("name", user.getPrenom() + " " + user.getNom());
            context.setVariable("resetUrl", resetUrl);
            
            String content = templateEngine.process("email/reset-password", context);
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Réinitialisation de votre mot de passe - Invoice Planner");
            helper.setText(content, true);
            
            mailSender.send(message);
            logger.info("Email de réinitialisation de mot de passe envoyé à : {}", user.getEmail());
            
        } catch (MessagingException e) {
            logger.error("Erreur lors de l'envoi de l'email de réinitialisation", e);
        }
    }
    
    @Async
    @Override
    public void sendWelcomeEmail(User user) {
        try {
            String loginUrl = baseUrl + "/auth/login";
            
            Context context = new Context();
            context.setVariable("name", user.getPrenom() + " " + user.getNom());
            context.setVariable("loginUrl", loginUrl);
            
            String content = templateEngine.process("email/welcome", context);
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Bienvenue sur Invoice Planner");
            helper.setText(content, true);
            
            mailSender.send(message);
            logger.info("Email de bienvenue envoyé à : {}", user.getEmail());
            
        } catch (MessagingException e) {
            logger.error("Erreur lors de l'envoi de l'email de bienvenue", e);
        }
    }
}
