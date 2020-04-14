package org.guiders.api.payload;

import lombok.Data;

public class AccountDto {

    @Data
    public static class InfoResponse {
        private Long id;
        private String email;
        private String username;
    }

}
