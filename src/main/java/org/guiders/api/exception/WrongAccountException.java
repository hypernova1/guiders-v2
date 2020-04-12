package org.guiders.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "잘못된 계정입니다. 계정을 다시 확인해주세요.")
public class WrongAccountException extends RuntimeException {
}
