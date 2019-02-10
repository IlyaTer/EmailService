package com.article.email.run.component;

import com.article.email.services.UserDistributionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements CommandLineRunner{

    private final UserDistributionService userDistributionService;

    public AppRunner(UserDistributionService userDistributionService) {
        this.userDistributionService = userDistributionService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        this.userDistributionService.sendDistribution();
    }//end run
}
