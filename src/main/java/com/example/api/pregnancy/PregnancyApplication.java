package com.example.api.pregnancy;

//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;


import com.example.api.pregnancy.models.User;
import com.example.api.pregnancy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@EnableJpaAuditing
@EnableJpaRepositories
@ConfigurationPropertiesScan
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@PropertySource("file:db.properties")
@EnableMongoRepositories
public class PregnancyApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PregnancyApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        if (this.userRepository.findAllByPhoneNumber("09396985633") == null) {
            User user = new User("VAHID ZAHEDI", "09396985633", passwordEncoder.encode("rokin123"), Arrays.asList("ADMIN"));

            this.userRepository.save(user);
        }
    }
}
