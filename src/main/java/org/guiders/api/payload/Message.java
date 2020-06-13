package org.guiders.api.payload;

import lombok.Data;

public class Message {
    private String content;

    @Data
    public static class Receiver {
        private String email;
    }
    
    @Data
    public static class Content {
        private String content;
    }

}
