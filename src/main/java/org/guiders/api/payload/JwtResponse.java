package org.guiders.api.payload;

import lombok.Data;

@Data
public class JwtResponse {

    private String accessToken;
    private final String tokenType;
    private AccountDto.InfoResponse userInfo;

    public JwtResponse(String accessToken, AccountDto.InfoResponse userInfo) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.userInfo = userInfo;
    }

}
