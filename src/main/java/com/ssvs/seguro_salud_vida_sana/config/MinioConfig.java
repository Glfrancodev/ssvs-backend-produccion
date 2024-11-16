package com.ssvs.seguro_salud_vida_sana.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("https://bucket-production-2738.up.railway.app")
                .credentials("eLtvyKfVoXH2PP5lvG09imHlBjzlZkzs", "kWRzfSKfy60YTG7O4mo42riI0VrUETeEAS669VLgbNGS3sHd")
                .build();
    }
}
