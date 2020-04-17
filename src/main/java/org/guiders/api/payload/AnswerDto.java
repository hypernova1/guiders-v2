package org.guiders.api.payload;

import lombok.Data;

public class AnswerDto {

    @Data
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private GuiderDto.DetailResponse guider;
    }

}
