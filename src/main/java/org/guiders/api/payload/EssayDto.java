package org.guiders.api.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

public class EssayDto {

    @Data
    public static class ResisterRequest {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Data
    public static class ResponseDetail {

    }

}
