package com.example.demo.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "ddwify7jw");
        config.put("api_key", "385248574235555");
        config.put("api_secret", "i8xkDFiyf6eYQo8NLkWRvfA8WBI");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
