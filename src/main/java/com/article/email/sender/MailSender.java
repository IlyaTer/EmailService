package com.article.email.sender;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MailSender {
    
    @Value("${data.email}")
    private String fromMail;
    
    @Value("${data.password}")
    private String fromPassword;
    
    
    public void sendMail(String subject, String content, String address) 
            throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMail,
                        fromPassword);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromMail, false));

        msg.setRecipients(Message.RecipientType.TO, 
                InternetAddress.parse(address));
        msg.setSubject(subject);
        msg.setContent(content, "text/html");
        msg.setSentDate(new Date());
        
        Transport.send(msg);
    }//end sendMail
   
}//end MailSender
