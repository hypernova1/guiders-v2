package org.guiders.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "해당 댓글이 존재하지 않습니다.")
public class CommentNotFoundException extends RuntimeException {
}
