package org.guiders.api.payload;

import lombok.Data;
import org.guiders.api.model.Name;

public class GuiderDto {

    public static class DetailResponse extends AccountDto.InfoResponse {
    }

    @Data
    public static class DetailRequest {
        private Name username;
        private String password;
    }

    @Data
    public static class Response {
        private Long id;
        private String email;
        private Name username;
    }


}
