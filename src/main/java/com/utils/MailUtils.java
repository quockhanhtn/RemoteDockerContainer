package com.utils;

import com.Cons;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
   static final String SMTP_HOST = "smtp.gmail.com";
   static final String SMTP_PORT = "587";

   static final String AUTH_MAIL_ADDRESS = System.getenv(Cons.KEY_AUTH_MAIL_ADDRESS);
   static final String AUTH_MAIL_USERNAME = System.getenv(Cons.KEY_AUTH_MAIL_USERNAME);
   static final String AUTH_MAIL_PASSWORD = System.getenv(Cons.KEY_AUTH_MAIL_PASSWORD);

   static Authenticator getAuthenticator() {
      return new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(AUTH_MAIL_USERNAME, AUTH_MAIL_PASSWORD);
         }
      };
   }

   static Properties getProperties() {
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", SMTP_HOST);
      props.put("mail.smtp.port", SMTP_PORT);
      return props;
   }

   public static boolean sendPlanText(String receiveEmail, String subject, Object body) {
      return send(receiveEmail, subject, body, "text/plain");
   }

   public static boolean sendHtmlText(String receiveEmail, String subject, Object body) {
      return send(receiveEmail, subject, body, "text/html");
   }

   public static boolean send(String receiveEmail, String subject, Object body, String contentType) {
      Session session = Session.getInstance(getProperties(), getAuthenticator());
      try {
         Message message = new MimeMessage(session);

         message.setFrom(new InternetAddress(AUTH_MAIL_ADDRESS));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveEmail));
         message.setSubject(subject);
         message.setContent(body, contentType + "; charset=UTF-8");

         // Send message
         Transport.send(message);
         return true;
      } catch (MessagingException e) {
         e.printStackTrace();
         return false;
      }
   }
}
