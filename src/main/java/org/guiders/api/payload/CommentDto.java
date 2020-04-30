package org.guiders.api.payload;

import lombok.Data;

public class CommentDto {

    @Data
    public static class Request {

        private String content;
        private Long essayId;

    }

    @Data
    public static class Response {
        private Long id;
        private String content;
        private AccountDto.InfoResponse writer;
    }

}
