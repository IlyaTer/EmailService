package com.article.email.services;

import com.article.email.model.ArticleDto;
import com.article.email.model.UserDto;
import com.article.email.sender.MailSender;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDistributionService {

    @Value("${user.uri}")
    private String userUri;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private RandomArticleService randomArticleService;

    private HttpHeaders headers;
    private HttpEntity<UserDto[]> entity;
    private RestTemplate rest;

    public UserDistributionService() {
    }

    {
        this.headers = new HttpHeaders();
        this.headers.setAccept(Arrays.asList(
                new MediaType[]{MediaType.APPLICATION_JSON}));

        this.entity = new HttpEntity<>(this.headers);
        
        this.rest = new RestTemplate();
    }

    @Async
    public void sendDistribution() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(300);
                ResponseEntity<UserDto[]> response = rest.exchange(this.userUri,
                        HttpMethod.GET, entity, UserDto[].class);

                StringBuilder address = new StringBuilder();
                UserDto[] users = response.getBody();
                if (users.length > 0) {
                    for (int i = 0; i < users.length; i++) {
                        if (users[i].getLogin().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) {
                            address.append(users[i].getLogin());
                            if (i != (users.length - 1)) {
                                address.append(",");
                            }
                        }//end mathif
                    }//end for

                    ArticleDto art = this.randomArticleService.getRandomArticle();
                    if (art != null) {
                        String subject = "Random article";
                        String content = "You can read this article by name " + art.getName();
                        mailSender.sendMail(subject, content, address.toString());
                        System.out.println(address.toString());
                    } else {
                        System.out.println("No article");
                    }
                }//end if
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }//end while
    }//end endDistribution
}//end UserDistributionService
