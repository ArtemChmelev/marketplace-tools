package ru.chmelev.controller_advace;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.chmelev.dto.ErrorDto;
import ru.chmelev.exception.NoFoundException;

@RestControllerAdvice
public class ExceptionAdvance {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleDataUnprocessableEntityException(MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorDto.setMessage(errorMessage);
        });
        return errorDto;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoFoundException.class)
    public ErrorDto handleDataNotFoundException(NoFoundException ex) {
        return new ErrorDto(ex.getMessage());

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorDto defaultHandleException(RuntimeException ex) {
        return new ErrorDto(ex.getMessage());

    }
}