package com.accenture.modulosPago;

import com.accenture.modulosPago.models.Account;
import com.accenture.modulosPago.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class ModulosPagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulosPagoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(AccountRepository accountRepository) {
		return (args) -> {

			Account account = new Account("2345676546897611234564", new BigDecimal(0), "43456", LocalDateTime.now());
			accountRepository.save(account);
		};
	}
}
