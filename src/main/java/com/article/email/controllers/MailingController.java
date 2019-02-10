package com.article.email.controllers;

import com.article.email.model.MailDataDto;
import com.article.email.model.UserDto;
import com.article.email.sender.MailSender;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailingController {

    @Autowired
    private MailSender mailSender;

    @RequestMapping(value = "/sendmailing", method = RequestMethod.POST)
    public MailDataDto sendMailing(@RequestBody MailDataDto mailData)
            throws AddressException, MessagingException, IOException {

        StringBuilder address = new StringBuilder();
        UserDto[] users = mailData.getUsers();

        if (users.length > 0) {

            for (int i = 0; i < users.length; i++) {
                address.append(users[i].getLogin());
                if (i != (users.length - 1)) {
                    address.append(",");
                }
            }

            mailSender.sendMail(mailData.getMessageSubject(),
                    mailData.getMessageContent(), address.toString());
        } else {
            System.out.println("INFO: No mails");
        }

        return mailData;
    }//end sendMailing
   
}
