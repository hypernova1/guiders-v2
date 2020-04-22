package org.guiders.api.payload;

import lombok.Data;
import org.guiders.api.model.Name;

public class AccountDto {

    @Data
    public static class InfoResponse {
        private Long id;
        private String email;
        private Name username;
        private String userType;
    }

}
