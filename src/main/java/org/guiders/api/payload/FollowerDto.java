package org.guiders.api.payload;

import lombok.Data;

public class FollowerDto {

    @Data
    public static class Response {
        private Long id;
        private String email;
        private String username;
    }

}
