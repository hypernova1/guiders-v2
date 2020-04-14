package org.guiders.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "존재하지 않는 계정입니다.")
public class AccountNotFoundException extends RuntimeException {
}
