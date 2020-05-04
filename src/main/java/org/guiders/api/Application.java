package org.guiders.api;

import org.guiders.api.constant.RoleName;
import org.guiders.api.domain.Follower;
import org.guiders.api.domain.Guider;
import org.guiders.api.domain.Role;
import org.guiders.api.repository.FollowerRepository;
import org.guiders.api.repository.GuiderRepository;
import org.guiders.api.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            GuiderRepository guiderRepository,
            FollowerRepository followerRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
            Role roleGuider = new Role(RoleName.ROLE_GUIDER);
            Role roleFollower = new Role(RoleName.ROLE_FOLLOWER);

            roleRepository.saveAll(Arrays.asList(roleAdmin, roleFollower, roleGuider));

            Guider guider = Guider.builder()
                    .email("chtlstjd01@gmail.com")
                    .firstName("sam chan")
                    .lastName("kwon")
                    .password(passwordEncoder.encode("1111"))
                    .build();
             guiderRepository.save(guider);

            Follower follower = Follower.builder()
                    .email("follower@naver.com")
                    .firstName("foll")
                    .lastName("lastt")
                    .password(passwordEncoder.encode("1111"))
                    .build();

            followerRepository.save(follower);
        };
    }

}
