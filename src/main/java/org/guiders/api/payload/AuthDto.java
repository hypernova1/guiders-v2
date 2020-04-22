package org.guiders.api.payload;

import lombok.Data;
import org.guiders.api.model.Name;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthDto {

    @Data
    public static class JoinRequest {

        @NotEmpty
        private String email;
        @NotNull
        private Name username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String userType;

        public boolean isGuider() {
            return userType.equals("guider");
        }

    }

    @Data
    public static class LoginRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String password;
        @NotEmpty
        private String userType;

        public boolean isGuider() {
            return userType.equals("guider");
        }
    }

    @Data
    public static class LoginResponse {
        private String email;
        private String username;
    }

    @Data
    public static class EmailRequest {
        @NotEmpty
        private String email;
    }

}
