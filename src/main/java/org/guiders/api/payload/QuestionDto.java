package org.guiders.api.payload;

import lombok.Data;

public class QuestionDto {

    @Data
    public static class Response {

        private Long id;
        private String title;
        private FollowerDto.Response follower;

    }

}
