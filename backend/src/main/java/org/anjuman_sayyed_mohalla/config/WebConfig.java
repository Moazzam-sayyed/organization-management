package org.anjuman_sayyed_mohalla.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("http://localhost:3000")  // Allow localhost:3000 origin
                .allowedOrigins("https://cosmic-buttercream-b96691.netlify.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);
    }

}
