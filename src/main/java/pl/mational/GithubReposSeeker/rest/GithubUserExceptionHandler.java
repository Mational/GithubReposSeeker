package pl.mational.GithubReposSeeker.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GithubUserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException unfe) {
        UserErrorResponse response = new UserErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(unfe.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
