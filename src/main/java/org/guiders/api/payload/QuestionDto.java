package org.guiders.api.payload;

import lombok.Data;
import org.guiders.api.domain.Question;

public class QuestionDto {

    @Data
    public static class Response {
        private Long id;
        private String title;
        private FollowerDto.Response writer;
    }

    @Data
    public static class DetailResponse {
        private Long id;
        private String title;
        private String content;
        private FollowerDto.Response writer;
        private AnswerDto.Response answer;
    }

    @Data
    public static class Request {
        private String title;
        private String content;
        private Long guiderId;
    }

    @Data
    public static class UpdateRequest {
        private String title;
        private String content;
    }

}
