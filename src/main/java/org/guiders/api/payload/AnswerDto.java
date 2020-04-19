package org.guiders.api.payload;

import lombok.Data;

public class AnswerDto {

    @Data
    public static class Response {
        private Long id;
        private String content;
        private GuiderDto.DetailResponse guider;
    }

    @Data
    public static class RegisterRequest {
        private Long questionId;
        private String content;
    }

    @Data
    public static class UpdateRequest {
        private String content;
    }
}
