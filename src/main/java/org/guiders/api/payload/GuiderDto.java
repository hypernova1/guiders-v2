package org.guiders.api.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class GuiderDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class DetailResponse extends AccountDto.InfoResponse {
    }

    @Data
    public static class DetailRequest {
        private String username;
        private String password;
    }

    @Data
    public static class Response {
        private Long id;
        private String email;
        private String username;
    }


}
