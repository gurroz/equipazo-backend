package com.equipazo;

import com.equipazo.adapter.rest.TeamDataConverter;
import com.equipazo.adapter.rest.TeamMemberConverter;
import com.equipazo.app.service.SaveTeamProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("application.properties")
@EnableConfigurationProperties(SaveTeamConfiguration.class)
public class AppConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TeamDataConverter());
        registry.addConverter(new TeamMemberConverter());
    }

    @Bean
    public SaveTeamProperties saveTeamProperties(SaveTeamConfiguration saveTeamConfiguration){
        return new SaveTeamProperties(saveTeamConfiguration.getFilesURI());
    }
}
