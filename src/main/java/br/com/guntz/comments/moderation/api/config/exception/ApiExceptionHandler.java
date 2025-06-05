package br.com.guntz.comments.moderation.api.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handler(MethodArgumentNotValidException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("One or more fields are invalid");
        problemDetail.setType(URI.create("https://guntz.guntz-comments-moderate.com.br/errors/fields-invalids"));

        var errors = ex.getFieldErrors().stream()
                .map(ErrorValidation::new)
                .toList();

        problemDetail.setProperty("fields", errors);

        return problemDetail;
    }

    @ExceptionHandler(InvalidUUIDException.class)
    public ProblemDetail handler(InvalidUUIDException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setTitle("Invalid UUID");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("/errors/invalid-uuid"));

        return problemDetail;
    }

    private record ErrorValidation(String field, String message) {
        public ErrorValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
