package com.ssvs.seguro_salud_vida_sana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeguroSaludVidaSanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguroSaludVidaSanaApplication.class, args);
	}

}
