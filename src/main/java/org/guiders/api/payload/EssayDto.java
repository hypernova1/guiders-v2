package org.guiders.api.payload;

import lombok.Data;
import org.guiders.api.constant.EssayType;

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
        private Long id;
        private String title;
        private String content;
        private EssayType essayType;
        private GuiderDto.DetailResponse writer;
        private int likeCnt;
        private int hits;
    }

    @Data
    public static class Response {
        private Long id;
        private String title;
        private EssayType essayType;
        private GuiderDto.Response writer;
        private int likeCnt;
        private int hits;
    }

    @Data
    public static class UpdateRequest {
        private String title;
        private String content;
    }
}
