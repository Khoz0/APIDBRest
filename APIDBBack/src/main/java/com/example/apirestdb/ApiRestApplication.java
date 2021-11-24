package com.example.apirestdb;

import com.example.apirestdb.crud.UserRepository;
import com.example.apirestdb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@SpringBootApplication
public class ApiRestApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ApiRestApplication.class, args);
    }

    @PostConstruct
    private void initDb(){
        // Le remplissage se fait à chaque lancement du serveur, mai sla bdd ne se vide pas
        /*String[] sqlStatements ={
                "INSERT INTO USER (name, email, password, city) VALUES('Marc', 'marc.marcus@oui.fr', 'azerty', 'Nancy')",
                "INSERT INTO USER (name, email, password, city) VALUES('Ginette', 'Supperette.Gigi@non.fr', 'password', 'Metz')",
                "INSERT INTO USER (name, email, password, city) VALUES('Manon', 'peutetre@non.com', 'malice', 'Paris')",
                "INSERT INTO USER (name, email, password, city) VALUES('Manon', 'peutetre2@non.com', 'malice2', 'Paris')"
        };
        Arrays.asList(sqlStatements).forEach(sql -> {
            jdbcTemplate.execute(sql);
        });*/


    }
}
