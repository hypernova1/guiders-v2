package org.guiders.api.payload;

import lombok.Data;

public class GuiderDto {

    public static class DetailResponse extends AccountDto.InfoResponse {
    }

    @Data
    public static class DetailRequest {
        private String firstName;
        private String lastName;
        private String password;
    }

    @Data
    public static class Response {
        private Long id;
        private String email;
        private String username;
    }


}
