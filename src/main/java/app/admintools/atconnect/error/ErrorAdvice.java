package app.admintools.atconnect.error;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorObject handleAuthException(AuthException exception, HttpServletRequest request) {
        return ErrorObject.builder()
                .name(exception.getClass().getName())
                .message(exception.getMessage())
                .path(request.getServletPath())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObject handleException(Exception exception, HttpServletRequest request) {
        return ErrorObject.builder()
                .name(exception.getClass().getName())
                .message(exception.getMessage())
                .path(request.getServletPath())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
