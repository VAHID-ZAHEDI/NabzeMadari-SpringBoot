package com.example.api.pregnancy;

//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@PropertySource("file:db.properties")
public class PregnancyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PregnancyApplication.class, args);
//        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


    }

}
