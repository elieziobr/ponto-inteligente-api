package com.curso.pontointeligente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication( exclude = { SecurityAutoConfiguration.class }) // Para ignorar a autenticação
public class PontoInteligenteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PontoInteligenteApplication.class, args);
    }

}
