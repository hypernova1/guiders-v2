package org.guiders.api;

import org.guiders.api.domain.Follower;
import org.guiders.api.domain.Guider;
import org.guiders.api.repository.FollowerRepository;
import org.guiders.api.repository.GuiderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            GuiderRepository guiderRepository,
            FollowerRepository followerRepository) {

        return args -> {
            Guider guider = Guider.builder()
                    .email("chtlstjd01@gmail.com")
                    .username("sam")
                    .password("1111")
                    .build();
             guiderRepository.save(guider);

            Follower follower = Follower.builder()
                    .email("follower@naver.com")
                    .username("foll")
                    .password("1111")
                    .build();

            followerRepository.save(follower);
        };
    }

}
