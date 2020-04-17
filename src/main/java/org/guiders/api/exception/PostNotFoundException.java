package org.guiders.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "해당 글을 찾을 수 없습니다.")
public class PostNotFoundException extends RuntimeException {
}
