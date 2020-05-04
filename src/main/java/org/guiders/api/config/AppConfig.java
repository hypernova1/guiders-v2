package org.guiders.api.config;

import org.guiders.api.domain.Account;
import org.guiders.api.domain.Comment;
import org.guiders.api.payload.AuthDto;
import org.guiders.api.payload.CommentDto;
import org.guiders.api.payload.GuiderDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
