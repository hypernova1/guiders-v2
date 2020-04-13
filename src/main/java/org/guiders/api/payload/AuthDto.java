package org.guiders.api.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

public class AuthDto {

    @Data
    public static class JoinRequest {

        @NotEmpty
        private String email;
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String userType;
    }

    @Data
    public static class LoginRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String password;
        @NotEmpty
        private String userType;
    }

    @Data
    public static class LoginResponse {
        private String email;
        private String username;
    }

    @Data
    public static class EmailRequest {
        @NotEmpty
        private String userType;
        @NotEmpty
        private String email;
    }

}
